package br.com.lucasaquiles.cogg.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by lucasaquiles on 13/11/16.
 */

public class CustomButtton extends Button {


    public CustomButtton(Context context){
        super(context);
        Typeface fontType=Typeface.createFromAsset(getContext().getAssets(), "fonts/cherl.tff");
        setTypeface(fontType);
    }

    public CustomButtton(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface fontType=Typeface.createFromAsset(getContext().getAssets(), "fonts/cherl.tff");
        setTypeface(fontType);
    }
}
