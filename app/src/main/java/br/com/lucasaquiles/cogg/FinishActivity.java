package br.com.lucasaquiles.cogg;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import br.com.lucasaquiles.cogg.bean.Pic;
import br.com.lucasaquiles.cogg.bean.Sketche;
import br.com.lucasaquiles.cogg.view.CustomTextView;

public class FinishActivity extends Activity {

    private CustomTextView title;
    private CustomTextView emocao;
    private ImageView image;
    private Pic pic;
    private Sketche current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);


        title = (CustomTextView) findViewById(R.id.title);
        emocao = (CustomTextView) findViewById(R.id.emocao);

        image = (ImageView) findViewById(R.id.imageView11);


        Intent i = getIntent();

        pic = (Pic) i.getSerializableExtra("pic");
        current = (Sketche) i.getSerializableExtra("current_sketch");


        Drawable draw = Drawable.createFromPath(current.getPathToAvatar());
        if (draw instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) draw;
            if (bitmapDrawable.getBitmap() != null) {

                Bitmap bitmap = bitmapDrawable.getBitmap();
                image.setImageBitmap(bitmap);
//                    textViewTitleImage.setText(title);
            }
        }

        title.setText(pic.getTitle());
        emocao.setText(pic.getEmotion());

    }

}
