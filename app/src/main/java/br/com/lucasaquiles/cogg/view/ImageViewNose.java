package br.com.lucasaquiles.cogg.view;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by lucasaquiles on 2/8/16.
 */
public class ImageViewNose extends ItemFaceView {

    @Override
    public boolean isNose() {
        return true;
    }

    public ImageViewNose(Context context) {
        super(context);
    }

    public ImageViewNose(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewNose(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
