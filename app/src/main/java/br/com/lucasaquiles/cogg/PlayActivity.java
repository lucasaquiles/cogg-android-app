package br.com.lucasaquiles.cogg;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.lucasaquiles.cogg.bean.ItemPic;
import br.com.lucasaquiles.cogg.bean.Pic;
import br.com.lucasaquiles.cogg.bean.Sketche;
import br.com.lucasaquiles.cogg.database.DatabaseHelper;
import br.com.lucasaquiles.cogg.utils.ImageUtils;
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
    private ImageButton backButton;
    private SeekBar seekBar;
    private TextView infoSeekBar;
    private TextView textViewTitleImage;
    private Button saveButton;
    private LinearLayout image;
    private Boolean config;
    private List<ItemPic> itensPic;

    private Pic pic;

    private int currentTab;
    static int tmpVal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);


        initializeComponents();


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

            config = intent.getBooleanExtra("config", false);

            pic = (Pic) intent.getSerializableExtra("pic");

            String filePath =  "";
            String title = "";
            if(extras != null) {
                filePath = extras.getString("filePath");
                title = extras.getString("title");
            }

            Drawable draw = Drawable.createFromPath(config?pic.getFilePath():filePath);
            if (draw instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) draw;
                if (bitmapDrawable.getBitmap() != null) {

                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    imageViewBase.setImageBitmap(bitmap);
//                    textViewTitleImage.setText(title);
                }
            }

        }


        List<ItemPic> itensPic = pic.loadItensPic(this);

        if(itensPic != null && !itensPic.isEmpty() && config){

            for (ItemPic item: itensPic) {

                Drawable drawable = this.getResources().getDrawable(item.getResourceId());
                String drawableName = getResources().getResourceEntryName(item.getResourceId());

                if(item.isHead()){

                    imageViewHead.setImageDrawable(drawable);
                    imageViewHead.setTag(drawableName);
                }

                if(item.isEye()){
                    imageViewEye.setImageDrawable(drawable);
                    imageViewEye.setTag(drawableName);
                }

                if(item.isEyebrow()){

                    imageViewEyebrow.setImageDrawable(drawable);
                    imageViewEyebrow.setTag(drawableName);
                }

                if(item.isHair()){

                    imageViewHair.setImageDrawable(drawable);
                    imageViewHair.setTag(drawableName);
                }

                if(item.isNoise()){

                    imageViewNose.setImageDrawable(drawable);
                    imageViewNose.setTag(drawableName);
                }

                if(item.isMouth()){

                    imageViewMouth.setImageDrawable(drawable);
                    imageViewMouth.setTag(drawableName);
                }
            }
        }

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
                           top = top + 5;
                       } else {

                           top = top - 5;
                       }

                      // infoSeekBar.setText("valor anterior: "+tmpVal+" prog: "+progress+ "margin-top: "+top);

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
                           top = top + 3;
                       } else {

                           top = top - 3 ;
                       }
                      // infoSeekBar.setText("valor anterior: "+tmpVal+" prog: "+progress+ "margin-top: "+top);
                       lp.setMargins(left, top, right, bottom);
                       imageViewEyebrow.setLayoutParams(lp);

                       tmpVal = progress;
                   }




                if(currentTab == 3){

                    // Toast.makeText(getApplicationContext(), "to no tab ", Toast.LENGTH_LONG).show();

                    FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                    FrameLayout.LayoutParams lpImage = (android.widget.FrameLayout.LayoutParams) imageViewMouth.getLayoutParams();
                    int left = lpImage.leftMargin;
                    int top = lpImage.topMargin;
                    int right = lpImage.rightMargin;
                    int bottom = lpImage.bottomMargin;

                    if (progress < tmpVal) {
                        top = top + 3;
                    } else {

                        top = top - 3 ;
                    }
                    // infoSeekBar.setText("valor anterior: "+tmpVal+" prog: "+progress+ "margin-top: "+top);
                    lp.setMargins(left, top, right, bottom);
                    imageViewMouth.setLayoutParams(lp);

                    tmpVal = progress;
                }

                if(currentTab == 4){

                    // Toast.makeText(getApplicationContext(), "to no tab ", Toast.LENGTH_LONG).show();

                    FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);

                  //  FrameLayout.LayoutParams lpImage = (android.widget.FrameLayout.LayoutParams) imageViewNose.getLayoutParams();

                    ViewGroup.MarginLayoutParams lpImage = (ViewGroup.MarginLayoutParams) imageViewNose.getLayoutParams();

                    int left = lpImage.leftMargin;
                    int top = lpImage.topMargin;
                    int right = lpImage.rightMargin;
                    int bottom = lpImage.bottomMargin;


                        if (progress < tmpVal) {
                            top = top + 3;
                        } else {

                            top = top - 3 ;
                        }


                    // infoSeekBar.setText("valor anterior: "+tmpVal+" prog: "+progress+ "margin-top: "+top);
                    lp.setMargins(left, top, right, bottom);
                    imageViewNose.setLayoutParams(lp);

                    tmpVal = progress;
                }

                if(currentTab == 5){

                    // Toast.makeText(getApplicationContext(), "to no tab ", Toast.LENGTH_LONG).show();

                    FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                    FrameLayout.LayoutParams lpImage = (android.widget.FrameLayout.LayoutParams) imageViewHair.getLayoutParams();
                    int left = lpImage.leftMargin;
                    int top = lpImage.topMargin;
                    int right = lpImage.rightMargin;
                    int bottom = lpImage.bottomMargin;

                    if (progress < tmpVal) {
                        top = top + 3;
                    } else {

                        top = top - 3 ;
                    }

                    lp.setMargins(left, top, right, bottom);
                    imageViewHair.setLayoutParams(lp);

                    tmpVal = progress;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                progress = 0;
              //  Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                progress = 0;
             //   Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
            }
        });



    }


    public void initializeComponents(){

        final RelativeLayout seekBarLayout = (RelativeLayout) findViewById(R.id.seekBarLayout);
        imageViewBase = (ImageView) findViewById(R.id.imageViewBase);
        image = (LinearLayout) findViewById(R.id.frameBase);
//        textViewTitleImage = (TextView) findViewById(R.id.title);

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
                seekBar.setProgress(5);

                if (currentTab == 0) {

                    seekBarLayout.setVisibility(View.INVISIBLE);
                }

                if (currentTab == 1 || currentTab == 2) {

                    seekBarLayout.setVisibility(View.VISIBLE);
                }

                Log.i("***Selected Tab", "Im currently in tab with index::" + currentTab);
            }
        });

        backButton = (ImageButton) findViewById(R.id.back_button);
        backButton.setOnClickListener(this);

        saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(this);

        imageViewBase = (ImageView) findViewById(R.id.imageViewBase);

        seekBar = (SeekBar) findViewById(R.id.seekBar);

        imageViewHead = (ImageView) findViewById(R.id.imageViewHead);
        imageViewEye = (ImageView) findViewById(R.id.imageViewEye);
        imageViewEyebrow = (ImageView) findViewById(R.id.imageViewEyewbrow);
        imageViewMouth = (ImageView) findViewById(R.id.imageViewMouth);
        imageViewNose = (ImageView) findViewById(R.id.imageViewNose);
        imageViewHair = (ImageView) findViewById(R.id.imageViewHair);
      //  infoSeekBar = (TextView) findViewById(R.id.textViewSeek);
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


    public boolean isValido(int id){


       // if(itensPic == null || itensPic.isEmpty()) {
            DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
            itensPic = new ArrayList<>();
            try {
                Dao<Pic, Integer> dao = DaoManager.createDao(databaseHelper.getConnectionSource(), Pic.class);
                Dao<ItemPic, Integer> skDao = DaoManager.createDao(databaseHelper.getConnectionSource(), ItemPic.class);

                dao.queryForId(pic.getId().intValue());
                itensPic = skDao.queryForEq("pic_id", this.pic);
            } catch (SQLException e) {
                e.printStackTrace();
            }
      //  }

        for (ItemPic item : itensPic) {
            Log.i("compare", "item: "+item.getId() + " == "+id);
            if(item.getResourceId() == id){

                return true;
            }
        }

        return false;
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == saveButton.getId()){

            image.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(image.getDrawingCache());
            image.setDrawingCacheEnabled(false);

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 40, bytes);

            String path = Environment.DIRECTORY_PICTURES + File.separator + "Cogg" ;
            File sdDir = Environment.getExternalStoragePublicDirectory(path);
            File fileDir = new File(sdDir, "templates");

            if (!fileDir.exists() && !fileDir.mkdirs()) {
                Toast.makeText(this, "Can't create directory to save image.",
                        Toast.LENGTH_LONG).show();
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
            Date data = new Date();
            String date = dateFormat.format(data);
            String filename = fileDir.getPath() + File.separator + date+"nomedaFoto.jpg";

            File f = new File(filename);
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            FileOutputStream fo = null;

            try {
                fo = new FileOutputStream(f);
                fo.write(bytes.toByteArray());
                fo.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());

            if(config) {

                pic.setAvatarPath(filename);
                try {

                    Dao<Pic, Integer> dao = DaoManager.createDao(databaseHelper.getConnectionSource(), Pic.class);
                    Dao<ItemPic, Integer> itensPicDao = DaoManager.createDao(databaseHelper.getConnectionSource(), ItemPic.class);

                    dao.assignEmptyForeignCollection(pic, "itensPic");

                    pic.getItensPic().clear();

                    ItemPic item1 = new ItemPic(ImageUtils.getResourceFromDrawable(PlayActivity.this, imageViewEye), pic);
                    item1.setEye(true);
                    pic.getItensPic().add(item1);

                    ItemPic eyeBrow = new ItemPic(ImageUtils.getResourceFromDrawable(PlayActivity.this, imageViewEyebrow), pic);
                    eyeBrow.setEyebrow(true);
                    pic.getItensPic().add(eyeBrow);

                    ItemPic obj = new ItemPic(ImageUtils.getResourceFromDrawable(PlayActivity.this, imageViewMouth), pic);
                    obj.setMouth(true);
                    pic.getItensPic().add(obj);

                    ItemPic obj1 = new ItemPic(ImageUtils.getResourceFromDrawable(PlayActivity.this, imageViewHead), pic);
                    obj1.setHead(true);
                    pic.getItensPic().add(obj1);

                    ItemPic obj2 = new ItemPic(ImageUtils.getResourceFromDrawable(PlayActivity.this, imageViewHair), pic);
                    obj2.setHair(true);
                    pic.getItensPic().add(obj2);

                    ItemPic obj3 = new ItemPic(ImageUtils.getResourceFromDrawable(this, imageViewNose), pic);
                    obj3.setNoise(true);
                    pic.getItensPic().add(obj3);

                    if (dao.update(pic) == 1) {
//                        AlertDialog alertDialog = new AlertDialog.Builder(PlayActivity.this).create();
//                        alertDialog.setTitle("Imagem atualizada");
//                        alertDialog.setMessage("A imagem foi configurada com sucesso");
//                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        dialog.dismiss();
//
//                                        Intent i = new Intent(PlayActivity.this, MainActivity.class);
//                                        startActivity(i);
//                                    }
//                                });
//                        alertDialog.show();


                        final Dialog dialog = new Dialog(this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.alert_dialog_layout);

                        dialog.setTitle("Imagem atualizada");

                        // set the custom dialog components - text, image and button
                        TextView text = (TextView) dialog.findViewById(R.id.text);
                        text.setText("A imagem foi configurada com sucesso");

                        ImageView imageBase = (ImageView) dialog.findViewById(R.id.imageBase);
                        imageBase.setImageBitmap(ImageUtils.getBitmapByPath(pic.getAvatarPath()));

                        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                        // if button is clicked, close the custom dialog
                        dialogButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();

                                Intent i = new Intent(PlayActivity.this, MainActivity.class);
                                startActivity(i);
                            }
                        });

                        dialog.show();
                    }
                } catch (SQLException e) {

                }

            }else{

                Sketche sk = new Sketche();
                sk.setPathImageBase(pic.getAvatarPath());

                sk.setPic(pic);
                sk.setPathToAvatar(filename);
                sk.setData(data);

                sk.setEyeResourceId(ImageUtils.getResourceFromDrawable(PlayActivity.this, imageViewEye));

                int eyebrow = ImageUtils.getResourceFromDrawable(PlayActivity.this, imageViewEyebrow);

                sk.setEyebrowResourceId(eyebrow);
                sk.setMouthResourceId(ImageUtils.getResourceFromDrawable(PlayActivity.this,  imageViewMouth));
                sk.setHeadResourceId(ImageUtils.getResourceFromDrawable(PlayActivity.this,  imageViewHead));
                sk.setHairResourceId(ImageUtils.getResourceFromDrawable(PlayActivity.this,   imageViewHair));
                sk.setNoiseResourceId(ImageUtils.getResourceFromDrawable(this, imageViewNose));

                try {
                    Dao<Pic, Integer> dao = DaoManager.createDao(databaseHelper.getConnectionSource(), Pic.class);

                    Dao<Sketche, Integer> sketcheDao = DaoManager.createDao(databaseHelper.getConnectionSource(), Sketche.class);

                    //if(sketcheDao.create(sk) == 1){

                        dao.assignEmptyForeignCollection(pic, "sketches");

                        pic.getSketches().add(sk);

                        if (dao.update(pic) == 1) {

                            Intent i = new Intent(this, FinishActivity.class);
                            i.putExtra("pic", pic);
                            i.putExtra( "current_sketch", sk);
                            startActivity(i);
                        }
                  //  }

                } catch (SQLException e) {
                    e.printStackTrace();
                }



            }
        }

        if(v.getId() == backButton.getId()){

            // adicionar um confirm dialog para voltar para o menu principal

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);

        }else{


            if(v instanceof ItemFaceView) {
                ImageView imageView = (ImageView) v;
                ItemFaceView faceItemView = (ItemFaceView) v;

                if (faceItemView.isEye()) {

                    imageView = imageViewEye;
                }

                if (faceItemView.isHead()) {

                    imageView = imageViewHead;
                }

                if (faceItemView.isEyebrow()) {

                    imageView = imageViewEyebrow;
                }

                if (faceItemView.isMouth()) {

                    imageView = imageViewMouth;
                }

                if (faceItemView.isNose()) {

                    imageView = imageViewNose;
                }

                if (faceItemView.isHair()) {

                    imageView = imageViewHair;
                }


                String name = this.getResources().getResourceName(imageView.getId());

                String openedDrawlable = faceItemView.getTag() + "_ic_opened";

                int id = getResources().getIdentifier(openedDrawlable, "drawable", getPackageName());


                if (!config) {
                    if (!isValido(id)) {

                        Toast.makeText(this, "Opa, esse não!", Toast.LENGTH_LONG).show();


                        final int RED = 0xffFF8080;

                        ValueAnimator colorAnim = ObjectAnimator.ofInt(v.getParent(), "backgroundColor", RED, Color.WHITE);
                        colorAnim.setDuration(3000);
                        colorAnim.setEvaluator(new ArgbEvaluator());
                        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
                        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
                        colorAnim.start();

                    } else {

                        final int RED = 0xffFF8080;

                        ValueAnimator colorAnim = ObjectAnimator.ofInt(v.getParent(), "backgroundColor", Color.GREEN, Color.WHITE);
                        colorAnim.setDuration(3000);
                        colorAnim.setEvaluator(new ArgbEvaluator());
                        colorAnim.setRepeatCount(ValueAnimator.INFINITE);

                        colorAnim.start();

                        Toast.makeText(this, "Muito bem!", Toast.LENGTH_SHORT).show();

                        //  Toast.makeText(this, openedDrawlable + v.getTag(), Toast.LENGTH_LONG ).show();
                        Drawable drawable = getResources().getDrawable(id);
                        // ((ImageView)v).setImageDrawable(drawable);


                        imageView.setImageDrawable(drawable);
                        imageView.setTag(openedDrawlable);

                    }

                    Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.scroll_item_animation);
                    imageView.startAnimation(hyperspaceJumpAnimation);

                    Animation pulseAnimation = AnimationUtils.loadAnimation(this, R.anim.pulse);
                    v.startAnimation(pulseAnimation);

                }else{

                    Drawable drawable = getResources().getDrawable(id);

                    imageView.setImageDrawable(drawable);
                    imageView.setTag(openedDrawlable);

                    Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.scroll_item_animation);
                    imageView.startAnimation(hyperspaceJumpAnimation);

                    Animation pulseAnimation = AnimationUtils.loadAnimation(this, R.anim.pulse);
                    v.startAnimation(pulseAnimation);
                }
            }
        }
    }

}
