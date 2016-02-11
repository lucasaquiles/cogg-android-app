package br.com.lucasaquiles.cogg.view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import br.com.lucasaquiles.cogg.R;

public class ImageConfigActivity extends Activity {

    private ImageView imageView;
    private EditText editTextTitle;
    private EditText editTextEmotion;
    private Button button;

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

                Toast.makeText(getApplicationContext(), "eita", Toast.LENGTH_LONG).show();
            }
        });


        String filePath = extras.getString("filePath");
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
