package br.com.lucasaquiles.cogg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.j256.ormlite.dao.DaoManager;

import java.sql.SQLException;
import java.util.List;

import br.com.lucasaquiles.cogg.bean.Pic;
import br.com.lucasaquiles.cogg.database.DatabaseHelper;
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
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.pulse);

        v.startAnimation(hyperspaceJumpAnimation);

        if(v.equals(buttonInit)){

            Intent i = new Intent(this,PlayActivity.class);

            try {
                List<Pic> list =  DaoManager.createDao(new DatabaseHelper(this).getConnectionSource(), Pic.class).queryForAll();

                if(list == null || list.isEmpty()){

                    startActivity(new Intent(this, SelectImageActivity.class));
                }else{

                    int position = (int) Math.random() * list.size();

                    Pic pic = list.get(position);

                    Intent intent = new Intent(this, PlayActivity.class);

                    intent.putExtra("filePath", pic.getAvatarPath());
                    intent.putExtra("title", pic.getTitle());
                    intent.putExtra("pic", pic);
                    intent.putExtra("config", false);

                    startActivity(intent);
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }

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
