package br.com.lucasaquiles.cogg.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.sql.SQLException;

import br.com.lucasaquiles.cogg.PlayActivity;
import br.com.lucasaquiles.cogg.R;
import br.com.lucasaquiles.cogg.bean.Pic;
import br.com.lucasaquiles.cogg.database.DatabaseHelper;

public class ImageConfigActivity extends Activity {

    private ImageView imageView;
    private EditText editTextTitle;
    private EditText editTextEmotion;
    private Button button;

    private String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_config);

        Bundle extras = getIntent().getExtras();
        Toast.makeText(this, "arquivo "+extras.getString("filePath"), Toast.LENGTH_LONG).show();


        imageView = (ImageView) findViewById(R.id.imageView10);
        editTextTitle = (EditText) findViewById(R.id.editText1);
        editTextEmotion = (EditText) findViewById(R.id.editText2);


        button =(Button) findViewById(R.id.buttonConfirm);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Pic pic = new Pic();
                pic.setEmotion(editTextEmotion.getText().toString());
                pic.setTitle(editTextTitle.getText().toString());
                pic.setAvatarPath("");
                pic.setFilePath(filePath);


                DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());

                try {

                    Dao<Pic, Integer> dao = DaoManager.createDao(databaseHelper.getConnectionSource(), Pic.class);

                    if(dao.create(pic) == 1){
                        Toast.makeText(getApplicationContext(), "Imagem salva", Toast.LENGTH_LONG).show();


                        Intent i = new Intent(getApplicationContext(), PlayActivity.class);
                        i.putExtra("filePath", filePath);
                        i.putExtra("pic", pic);

                        startActivity(i);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }
        });


        filePath = extras.getString("filePath");
        Drawable draw = Drawable.createFromPath(filePath);
        if (draw instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) draw;
            if (bitmapDrawable.getBitmap() != null) {

                Bitmap bitmap = bitmapDrawable.getBitmap();
                imageView.setImageBitmap(bitmap);
            }
        }

        // Bitmap b = BitmapFactory.decodeByteArray(
        // getIntent().getByteArrayExtra("byteArray"), 0, getIntent().getByteArrayExtra("byteArray").length);

    }

}
