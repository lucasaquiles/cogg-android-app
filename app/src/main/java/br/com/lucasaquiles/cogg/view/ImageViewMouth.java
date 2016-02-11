package br.com.lucasaquiles.cogg.view;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by lucasaquiles on 12/30/15.
 */
public class ImageViewMouth extends ItemFaceView {



    public ImageViewMouth(Context context) {
        super(context);
    }

    public ImageViewMouth(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewMouth(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean isMouth() {
        return true;
    }
}
