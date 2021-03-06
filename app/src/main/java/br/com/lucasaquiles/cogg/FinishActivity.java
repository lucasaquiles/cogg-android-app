package br.com.lucasaquiles.cogg;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.plattysoft.leonids.ParticleSystem;

import br.com.lucasaquiles.cogg.bean.Pic;
import br.com.lucasaquiles.cogg.bean.Sketche;
import br.com.lucasaquiles.cogg.view.CustomButtton;
import br.com.lucasaquiles.cogg.view.CustomTextView;

public class FinishActivity extends Activity {

    private CustomTextView title;
    private CustomTextView emocao;
    private ImageView image;
    private Pic pic;
    private Sketche current;
    private CustomButtton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        backButton = (CustomButtton) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(v.getId() == backButton.getId()){

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }
            }
        });


        title = (CustomTextView) findViewById(R.id.title);
        emocao = (CustomTextView) findViewById(R.id.emocao);

        image = (ImageView) findViewById(R.id.imageView11);


        Intent i = getIntent();

        pic = (Pic) i.getSerializableExtra("pic");
        current = (Sketche) i.getSerializableExtra("current_sketch");


        new ParticleSystem(this, 80, R.drawable.star1, 10000)
                .setSpeedModuleAndAngleRange(0f, 0.1f, 0, 0)
                .setRotationSpeed(144)
                .setAcceleration(0.000017f, 90)
                .setFadeOut(200, new AccelerateInterpolator())

                .emitWithGravity(findViewById(R.id.emiter_top_right), Gravity.BOTTOM, 20);

        new ParticleSystem(this, 80, R.drawable.star2, 10000)
                .setSpeedModuleAndAngleRange(0f, 0.1f, 0, 0)
                .setRotationSpeed(144)
                .setAcceleration(0.000017f, 90)
                .setFadeOut(200, new AccelerateInterpolator())

                .emitWithGravity(findViewById(R.id.emiter_top_left), Gravity.BOTTOM, 30);

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
