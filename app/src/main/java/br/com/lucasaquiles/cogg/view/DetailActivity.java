package br.com.lucasaquiles.cogg.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;

import br.com.lucasaquiles.cogg.R;
import br.com.lucasaquiles.cogg.bean.Pic;
import br.com.lucasaquiles.cogg.bean.Sketche;
import br.com.lucasaquiles.cogg.utils.ImageUtils;

public class DetailActivity extends Activity {

    private ImageView image;
    private ImageView imageBase;
    private ImageView avatarBase;
    private CustomTextView text;
    private TextView titulo;
    private TextView emocao;
    private Pic pic;
    private Sketche currentSketch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        image = (ImageView) findViewById(R.id.image) ;
        imageBase = (ImageView) findViewById(R.id.imageBase) ;
        avatarBase = (ImageView) findViewById(R.id.avatarBase) ;
        text = (CustomTextView) findViewById(R.id.flag);

        titulo = (TextView) findViewById(R.id.titulo);
        emocao = (TextView) findViewById(R.id.emocao);

        Intent intent = getIntent();

        currentSketch = (Sketche) intent.getSerializableExtra("current");

        Bitmap base = ImageUtils.getBitmapByPath(currentSketch.getPic().getAvatarPath());
        Bitmap custom = ImageUtils.getBitmapByPath(currentSketch.getPathToAvatar());


        Bitmap real = ImageUtils.findDifference(base, custom);


        titulo.setText(currentSketch.getPic().getTitle());
        emocao.setText(currentSketch.getPic().getEmotion());

        double difere = ImageUtils.calcDiference(base, custom);

        String strValorPercentual = String.valueOf(BigDecimal.valueOf(difere).setScale(2, BigDecimal.ROUND_HALF_UP));

        text.setText(strValorPercentual);

        image.setImageBitmap(real);
        imageBase.setImageBitmap(ImageUtils.getBitmapByPath(currentSketch.getPic().getFilePath()));
        avatarBase.setImageBitmap(ImageUtils.getBitmapByPath(currentSketch.getPic().getAvatarPath()));

    }

}
