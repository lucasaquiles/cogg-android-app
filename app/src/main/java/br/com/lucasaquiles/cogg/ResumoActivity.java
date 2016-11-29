package br.com.lucasaquiles.cogg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.lucasaquiles.cogg.adapter.ResumoListAdapter;
import br.com.lucasaquiles.cogg.bean.Pic;
import br.com.lucasaquiles.cogg.bean.Sketche;
import br.com.lucasaquiles.cogg.database.DatabaseHelper;
import br.com.lucasaquiles.cogg.view.DetailActivity;

public class ResumoActivity extends Activity implements AdapterView.OnItemClickListener {

    private Pic pic;
    private ImageView image;
    private ResumoListAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);

        listView = (ListView) findViewById(R.id.lista) ;

        Intent i = getIntent();

        pic = (Pic) i.getSerializableExtra("pic");

        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        List<Sketche> sks = new ArrayList<>();
        try {
            Dao<Pic, Integer> dao = DaoManager.createDao(databaseHelper.getConnectionSource(), Pic.class);
            Dao<Sketche, Integer> skDao = DaoManager.createDao(databaseHelper.getConnectionSource(), Sketche.class);

            dao.queryForId(pic.getId().intValue());
            sks = skDao.queryForEq("pic_id", this.pic);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        adapter = new ResumoListAdapter(this, R.layout.resumo_item_list, sks);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(this);
//        image = (ImageView) findViewById(R.id.image);
//
//        Drawable draw = Drawable.createFromPath(pic.getAvatarPath());
//        if (draw instanceof BitmapDrawable) {
//            BitmapDrawable bitmapDrawable = (BitmapDrawable) draw;
//            if (bitmapDrawable.getBitmap() != null) {
//
//                Bitmap bitmap = bitmapDrawable.getBitmap();
//                image.setImageBitmap(bitmap);
////                    textViewTitleImage.setText(title);
//            }
//        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Posicaao: "+position, Toast.LENGTH_LONG).show();
        Sketche stetche = (Sketche) parent.getItemAtPosition(position);

        Intent i = new Intent(this, DetailActivity.class);
        i.putExtra("current", stetche);
        startActivity(i);
    }
}
