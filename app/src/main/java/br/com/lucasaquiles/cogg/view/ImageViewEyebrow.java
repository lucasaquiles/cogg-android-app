package br.com.lucasaquiles.cogg.view;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by lucasaquiles on 12/29/15.
 */
public class ImageViewEyebrow extends ItemFaceView {
    public ImageViewEyebrow(Context context) {
        super(context);
    }

    public ImageViewEyebrow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewEyebrow(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean isEyebrow() {
        return true;
    }
}
