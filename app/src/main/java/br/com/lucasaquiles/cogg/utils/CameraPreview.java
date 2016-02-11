package br.com.lucasaquiles.cogg.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * Created by lucasaquiles on 2/4/16.
 */
public class CameraPreview  extends SurfaceView {


    public CameraPreview(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
    }

    public CameraPreview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CameraPreview(Context context) {
        super(context);
    }

}
