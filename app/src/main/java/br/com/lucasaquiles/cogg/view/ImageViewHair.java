package br.com.lucasaquiles.cogg.view;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by lucasaquiles on 2/10/16.
 */
public class ImageViewHair extends ItemFaceView {
    public ImageViewHair(Context context) {
        super(context);
    }

    public ImageViewHair(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewHair(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean isHair() {
       return true;
    }
}
