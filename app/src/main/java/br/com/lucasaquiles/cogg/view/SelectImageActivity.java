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

import java.io.File;
import java.util.ArrayList;

import br.com.lucasaquiles.cogg.PlayActivity;
import br.com.lucasaquiles.cogg.R;
import br.com.lucasaquiles.cogg.adapter.GridViewAdapter;

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

        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/Cogg");


        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inSampleSize = 4;
        o.inDither=false;                     //Disable Dithering mode
        o.inPurgeable=true;
//

        if(path.exists()) {
            String[] fileNames = path.list();

            for(int i = 0; i < fileNames.length; i++) {

                String filePath = path.getPath() + "/" + fileNames[i];

               // File image = new File(filePath, "teste");
//                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
//                Bitmap bitmap = BitmapFactory.decodeFile(filePath,bmOptions);
//                bitmap = Bitmap.createScaledBitmap(bitmap, 80,80,true);

                Drawable draw = Drawable.createFromPath(filePath);
                if (draw instanceof BitmapDrawable) {
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) draw;
                    if (bitmapDrawable.getBitmap() != null) {

                        Bitmap bitmap = bitmapDrawable.getBitmap();
                        imageItems.add(new ImageItem(bitmap, fileNames[i],  filePath));
                    }
                }

             //       Bitmap bitmap = BitmapFactory.decodeFile();



               // Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(new File(), null, o);

              //  BitmapDrawable bitmap =  new BitmapDrawable(getResources(), path.getPath()+"/"+ fileNames[i]);

             //
            }
        }



        //new BitmapDrawable(getResources(), path+".jpg");


//
//        Resources res = this.getResources();
//        int id = R.drawable.template;
//        Bitmap b = BitmapFactory.decodeResource(res, id, o);
//        imageItems.add(new ImageItem(b, "Image#", id));
//
//        int idAngry = R.drawable.angry;
//        Bitmap b2 = BitmapFactory.decodeResource(res, idAngry, o);
//
//        int idSad = R.drawable.sad;
//        Bitmap b3 = BitmapFactory.decodeResource(res, idSad, o);
//
//        int idHappy = R.drawable.happy;
//        Bitmap b4 = BitmapFactory.decodeResource(res, idHappy, o);
//
//
//        imageItems.add(new ImageItem(b2, "Raiva", idAngry));
//        imageItems.add(new ImageItem(b3, "Triste", idSad));
//        imageItems.add(new ImageItem(b4, "Feliz", idHappy));


        //}
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
        }
        //Start details activity
        startActivity(intent);
    }
}
