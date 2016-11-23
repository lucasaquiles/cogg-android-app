package br.com.lucasaquiles.cogg.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import br.com.lucasaquiles.cogg.R;
import br.com.lucasaquiles.cogg.bean.Pic;
import br.com.lucasaquiles.cogg.bean.Sketche;
import br.com.lucasaquiles.cogg.utils.ImageUtils;

public class DetailActivity extends Activity {

    private ImageView image;
    private CustomTextView text;
    private Pic pic;
    private Sketche currentSketch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        image = (ImageView) findViewById(R.id.image) ;
        text = (CustomTextView) findViewById(R.id.flag);

        Intent intent = getIntent();

        currentSketch = (Sketche) intent.getSerializableExtra("current");

        Bitmap base = ImageUtils.getBitmapByPath(currentSketch.getPic().getAvatarPath());
        Bitmap custom = ImageUtils.getBitmapByPath(currentSketch.getPathToAvatar());

        Bitmap real = ImageUtils.findDifference(base, custom);

        text.setText(currentSketch.getPic().getTitle());

        image.setImageBitmap(real);
    }

}
