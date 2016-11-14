package br.com.lucasaquiles.cogg.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by lucasaquiles on 12/11/16.
 */

public class CustomTextView extends TextView {
    public CustomTextView(Context context) {

        super(context);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/cheri.tff"));
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/cheri.tff"));

    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/cheri.tff"));
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/cheri.tff"));
    }
}
