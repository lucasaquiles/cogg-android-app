package br.com.lucasaquiles.cogg.view;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by lucasaquiles on 12/28/15.
 */
public class ImageViewEye extends ItemFaceView {

    @Override
    public boolean isEye() {
        return true;
    }

    public ImageViewEye(Context context) {
        super(context);
    }

    public ImageViewEye(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewEye(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
