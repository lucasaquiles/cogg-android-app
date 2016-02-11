package br.com.lucasaquiles.cogg;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.sql.SQLException;

import br.com.lucasaquiles.cogg.bean.Pic;
import br.com.lucasaquiles.cogg.database.DatabaseHelper;
import br.com.lucasaquiles.cogg.view.ItemFaceView;


public class PlayActivity extends Activity implements View.OnClickListener {

    private TabHost tabHost;
    private HorizontalScrollView scrollView;
    private ImageView imageViewHead;
    private ImageView imageViewEye;
    private ImageView imageViewBase;
    private ImageView imageViewEyebrow;
    private ImageView imageViewMouth;
    private ImageView imageViewNose;
    private ImageView imageViewHair;
    private Button backButton;
    private SeekBar seekBar;
    private TextView infoSeekBar;

    private int currentTab;
    static int tmpVal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);


        initializeComponents();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
               progress = progresValue;

                   boolean increase = progress < tmpVal;

                   if(currentTab == 1) {


//                       ajustPosition(imageViewEye, increase );

                       FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                       FrameLayout.LayoutParams lpImage = (android.widget.FrameLayout.LayoutParams) imageViewEye.getLayoutParams();
                       int left = lpImage.leftMargin;
                       int top = lpImage.topMargin;
                       int right = lpImage.rightMargin;
                       int bottom = lpImage.bottomMargin;

                       if (progress < tmpVal) {
                           top = top - 5;
                       } else {

                           top = top + 5;
                       }

                       infoSeekBar.setText("valor anterior: "+tmpVal+" prog: "+progress+ "margin-top: "+top);

                       tmpVal = progress;

                       lp.setMargins(left, top, right, bottom);
                       imageViewEye.setLayoutParams(lp);

                   }

                   if(currentTab == 2){

                      // Toast.makeText(getApplicationContext(), "to no tab ", Toast.LENGTH_LONG).show();

                       FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                       FrameLayout.LayoutParams lpImage = (android.widget.FrameLayout.LayoutParams) imageViewEyebrow.getLayoutParams();
                       int left = lpImage.leftMargin;
                       int top = lpImage.topMargin;
                       int right = lpImage.rightMargin;
                       int bottom = lpImage.bottomMargin;

                       if (progress < tmpVal) {
                           top = top - 3;
                       } else {

                           top = top + 3 ;
                       }
                       infoSeekBar.setText("valor anterior: "+tmpVal+" prog: "+progress+ "margin-top: "+top);
                       lp.setMargins(left, top, right, bottom);
                       imageViewEyebrow.setLayoutParams(lp);

                       tmpVal = progress;
                   }



//
//                   lp.setMargins(left, top, right, bottom);
//                   imageViewEye.setLayoutParams(lp);

//                    case 2 :  break;
//                    case 3 :  break;
//                    case 4 :  break;


               // Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
              //  Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

             //   Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
            }
        });


        Intent intent = getIntent();

       // Bitmap b = BitmapFactory.decodeByteArray(
       // getIntent().getByteArrayExtra("byteArray"), 0, getIntent().getByteArrayExtra("byteArray").length);

        Bundle extras = intent.getExtras();

        if(extras != null && extras.getInt("byteArray") > 0) {
            int resourceId = extras.getInt("byteArray");


            Resources res = this.getResources();

            Bitmap b = BitmapFactory.decodeResource(res, resourceId);

            imageViewBase.setImageBitmap(b);
        }else{

            String filePath = extras.getString("filePath");
            Drawable draw = Drawable.createFromPath(filePath);
            if (draw instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) draw;
                if (bitmapDrawable.getBitmap() != null) {

                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    imageViewBase.setImageBitmap(bitmap);
                }
            }

        }

    }

    public void initializeComponents(){
        imageViewBase = (ImageView) findViewById(R.id.imageViewBase);

        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("tab_head");
        spec.setIndicator("",getResources().getDrawable(R.drawable.ic_tab_head));
        spec.setContent(R.id.scrollItemLayout);

        tabHost.getTabWidget().setStripEnabled(false);

        tabHost.addTab(createTab("tab_head", "Tag head", R.id.scrollItemLayout, R.drawable.ic_tab_head));
        tabHost.addTab(createTab("tab_creation3", "Tag eye", R.id.scrollItemEye, R.drawable.ic_tab_eye));
        tabHost.addTab(createTab("tab_creation4", "Tag hair", R.id.scrollItemEyebrow, R.drawable.ic_tab_eyebrow));

        tabHost.addTab(createTab("tab_creation5", "Tag mouth", R.id.scrollItemMouth, R.drawable.ic_tab_mouth));
        tabHost.addTab(createTab("tab_creation5", "Tag nose", R.id.scrollItemNose, R.drawable.ic_tab_nose));
        tabHost.addTab(createTab("tab_creation5", "Tag hair", R.id.scrollItemHair, R.drawable.ic_tab_nose));

        // Display display = getWindowManager().getDefaultDisplay();
        int width = 200;

        tabHost.setCurrentTab(0);

        tabHost.getTabWidget().getChildAt(0).setLayoutParams(new LinearLayout.LayoutParams((width / 2) - 2, 50));
        tabHost.getTabWidget().getChildAt(1).setLayoutParams(new LinearLayout.LayoutParams((width / 2) - 2, 50));
        tabHost.getTabWidget().getChildAt(2).setLayoutParams(new LinearLayout.LayoutParams((width / 2) - 2, 50));
        tabHost.getTabWidget().getChildAt(3).setLayoutParams(new LinearLayout.LayoutParams((width / 2) - 2, 50));
        tabHost.getTabWidget().getChildAt(4).setLayoutParams(new LinearLayout.LayoutParams((width / 2) - 2, 50));
        tabHost.getTabWidget().getChildAt(5).setLayoutParams(new LinearLayout.LayoutParams((width / 2) - 2, 50));

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String arg0) {
                tmpVal = 0;
                currentTab = tabHost.getCurrentTab();

                if (currentTab == 0) {

                    seekBar.setVisibility(View.INVISIBLE);
                }

                if (currentTab == 1 || currentTab == 2) {

                    seekBar.setVisibility(View.VISIBLE);
                }

                Log.i("***Selected Tab", "Im currently in tab with index::" + currentTab);
            }
        });

        backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(this);

        imageViewBase = (ImageView) findViewById(R.id.imageViewBase);

        seekBar = (SeekBar) findViewById(R.id.seekBar);

        imageViewHead = (ImageView) findViewById(R.id.imageViewHead);
        imageViewEye = (ImageView) findViewById(R.id.imageViewEye);
        imageViewEyebrow = (ImageView) findViewById(R.id.imageViewEyewbrow);
        imageViewMouth = (ImageView) findViewById(R.id.imageViewMouth);
        imageViewNose = (ImageView) findViewById(R.id.imageViewNose);
        imageViewHair = (ImageView) findViewById(R.id.imageViewHair);
        infoSeekBar = (TextView) findViewById(R.id.textViewSeek);
    }


    private TabHost.TabSpec createTab(final String tag,final String title, final int drawable, int image){

        final View tab = LayoutInflater.from(tabHost.getContext()).inflate(R.layout.tab, null);
      //  ((TextView)tab.findViewById(R.id.tab_text)).setText(title);
        ((ImageView)tab.findViewById(R.id.tab_icon)).setImageResource(image);


        return tabHost.newTabSpec(tag).setIndicator(tab).setContent(drawable);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_play, menu);
        return true;
    }

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

        if(v.getId() == backButton.getId()){

            // adicionar um confirm dialog para voltar para o menu principal

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);

        }else{
            ImageView imageView = (ImageView) v;

            if(imageView instanceof ItemFaceView){

                ItemFaceView faceItemView = (ItemFaceView) v;

                if(faceItemView.isEye()){

                    imageView = imageViewEye;
                }

                if(faceItemView.isHead()){

                    imageView = imageViewHead;
                }

                if(faceItemView.isEyebrow()){

                    imageView = imageViewEyebrow;
                }

                if(faceItemView.isMouth()){

                    imageView = imageViewMouth;
                }

                if(faceItemView.isNose()){

                    imageView = imageViewNose;
                }

                if(faceItemView.isHair()){

                    imageView = imageViewHair;
                }


                String name = this.getResources().getResourceName(imageView.getId());

                String openedDrawlable = faceItemView.getTag()+"_ic_opened";

                int id = getResources().getIdentifier(openedDrawlable, "drawable", getPackageName());
                //  Toast.makeText(this, openedDrawlable + v.getTag(), Toast.LENGTH_LONG ).show();
                Drawable drawable = getResources().getDrawable(id);
                // ((ImageView)v).setImageDrawable(drawable);
                Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.scroll_item_animation);

                imageView.setImageDrawable(drawable);
                imageView.startAnimation(hyperspaceJumpAnimation);


                Animation pulseAnimation = AnimationUtils.loadAnimation(this, R.anim.pulse);
                v.startAnimation(pulseAnimation);
            }





           // imageViewHead.setBackgroundResource(id);
            //Toast.makeText(this, name, Toast.LENGTH_LONG ).show();

           // imageView.draw();
        }
    }

}
