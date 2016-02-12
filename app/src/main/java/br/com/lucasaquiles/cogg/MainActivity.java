package br.com.lucasaquiles.cogg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import br.com.lucasaquiles.cogg.view.SelectImageActivity;


public class MainActivity extends Activity implements View.OnClickListener{

    private Button buttonInit;
    private Button btnNewPic;
    private Button btnChoosePick;
    android.graphics.Camera camera;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        buttonInit =  (Button) findViewById(R.id.buttonInit);
        buttonInit.setOnClickListener(this);

        btnNewPic = (Button) findViewById(R.id.btnNewPic);
        btnNewPic.setOnClickListener(this);

        btnChoosePick = (Button) findViewById(R.id.btnChoosePic);
        btnChoosePick.setOnClickListener(this);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        //getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if(v.equals(buttonInit)){

            Intent i = new Intent(this,PlayActivity.class);

            startActivity(i);

        }

        if(v.equals(btnNewPic)){

            Intent i = new Intent(this, TakePictureActivity.class);
            startActivity(i);
        }

        if(v.equals(btnChoosePick)){

            Intent i = new Intent(this, SelectImageActivity.class);
            startActivity(i);
        }
    }

    private void initCamera(){

        try {

        }catch(Exception e){

        }


    }
}
