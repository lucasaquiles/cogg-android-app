package br.com.lucasaquiles.cogg.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.j256.ormlite.dao.DaoManager;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.lucasaquiles.cogg.PlayActivity;
import br.com.lucasaquiles.cogg.R;
import br.com.lucasaquiles.cogg.adapter.GridViewAdapter;
import br.com.lucasaquiles.cogg.bean.Pic;
import br.com.lucasaquiles.cogg.database.DatabaseHelper;

public class SelectImageActivity extends Activity implements AdapterView.OnItemClickListener{

    private GridView gridView;
    private GridViewAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_image);

        gridView = (GridView) findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, getData());
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(this);
    }


    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();

        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inSampleSize = 4;
        o.inDither=false;                     //Disable Dithering mode
        o.inPurgeable=true;

        try {

            List<Pic> list =  DaoManager.createDao(new DatabaseHelper(this).getConnectionSource(), Pic.class).queryForAll();

            for (Pic pic : list) {

                Drawable draw = Drawable.createFromPath(pic.getFilePath());


                if (draw != null && draw instanceof BitmapDrawable) {
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) draw;
                    if (bitmapDrawable.getBitmap() != null) {

                        Bitmap bitmap = bitmapDrawable.getBitmap();
                        imageItems.add(new ImageItem(bitmap, pic.getTitle(),  pic.getFilePath()));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/Cogg");

//        if(path.exists()) {
//            String[] fileNames = path.list();
//
//            for(int i = 0; i < fileNames.length; i++) {
//
//                String filePath = path.getPath() + "/" + fileNames[i];
//
//                Drawable draw = Drawable.createFromPath(filePath);
//                if (draw instanceof BitmapDrawable) {
//                    BitmapDrawable bitmapDrawable = (BitmapDrawable) draw;
//                    if (bitmapDrawable.getBitmap() != null) {
//
//                        Bitmap bitmap = bitmapDrawable.getBitmap();
//                        imageItems.add(new ImageItem(bitmap, fileNames[i],  filePath));
//                    }
//                }
//            }
//        }

        return imageItems;
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ImageItem item = (ImageItem) parent.getItemAtPosition(position);
        //Create intent
        Intent intent = new Intent(this, PlayActivity.class);
        //intent.putExtra("title", item.getTitle());
        //intent.putExtra("image", item.getImage());
        //ByteArrayOutputStream bs = new ByteArrayOutputStream();
        //item.getImage().compress(Bitmap.CompressFormat.JPEG, 50, bs);

        if(item.getResourceId() > 0) {
            intent.putExtra("byteArray", item.getResourceId());
        }else {
            intent.putExtra("filePath", item.getFilePath());
            intent.putExtra("title", item.getTitle());
        }
        //Start details activity
        startActivity(intent);
    }
}
