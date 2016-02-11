package br.com.lucasaquiles.cogg.view;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by lucasaquiles on 12/28/15.
 */
public class ImageViewHead extends ItemFaceView {

    @Override
    public boolean isHead() {
        return true;
    }

    public ImageViewHead(Context context) {
        super(context);
    }

    public ImageViewHead(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewHead(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
