package br.com.lucasaquiles.cogg.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;


/**
 * Created by lucasaquiles on 2/17/15.
 */
public class ItemFaceView extends ImageView{

    private boolean checked = false;

    public boolean isEye(){
        return false;
    }

    public boolean isHair(){
        return false;
    }

    public boolean isHead(){
        return false;
    }

    public boolean isMouth(){
        return false;
    }

    public boolean isEyebrow(){
        return false;
    }

    public boolean isNose(){return false;}




    public ItemFaceView(Context context) {
        super(context);
    }

    public ItemFaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemFaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
