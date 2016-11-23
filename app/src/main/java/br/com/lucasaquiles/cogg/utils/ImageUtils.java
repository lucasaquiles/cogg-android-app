package br.com.lucasaquiles.cogg.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucasaquiles on 12/16/15.
 */
public class ImageUtils {
    private static final int threashold = 10;
    public void onDraw(ImageView image) {

        Bitmap workingBitmap =  Bitmap.createBitmap(((BitmapDrawable) image.getDrawable()).getBitmap());
        Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(mutableBitmap);
//
        Bitmap roundBitmap =  getCroppedBitmap(mutableBitmap, 100);
       // canvas.drawBitmap(roundBitmap, 0,0, null);

      //  image.setImageBitmap(roundBitmap);
        image.setImageDrawable(new BitmapDrawable(image.getResources(), roundBitmap));
    }

    public static Bitmap getCroppedBitmap(Bitmap bmp, int radius) {

        Bitmap sbmp;
        if (bmp.getWidth() != radius || bmp.getHeight() != radius) {
            float smallest = Math.min(bmp.getWidth(), bmp.getHeight());
            float factor = smallest / radius;
            sbmp = Bitmap.createScaledBitmap(bmp, (int) (bmp.getWidth() / factor), (int) (bmp.getHeight() / factor), false);
        } else {
            sbmp = bmp;
        }

        Bitmap output = Bitmap.createBitmap(radius, radius,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xffa19774;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, radius, radius);

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(radius / 2 + 0.7f,
                radius / 2 + 0.7f, radius / 2 + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(sbmp, rect, rect, paint);

        return sbmp;
    }


    public static Bitmap getBitmapByPath(String path){

        Drawable draw = Drawable.createFromPath(path);
        if (draw instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) draw;
            if (bitmapDrawable.getBitmap() != null) {

                Bitmap bitmap = bitmapDrawable.getBitmap();

                return bitmap;
//                image.setImageBitmap(bitmap);
//                    textViewTitleImage.setText(title);
            }
        }

        return null;
    }




    public static Bitmap findDifference(Bitmap firstImage, Bitmap secondImage) {
        Bitmap bmp = secondImage.copy(secondImage.getConfig(), true);

        List<Integer> pixels = new ArrayList<Integer>();

        if (firstImage.getWidth() != secondImage.getWidth()
                || firstImage.getHeight() != secondImage.getHeight()) {
            return null;
        }

        for (int i = 0; i < firstImage.getWidth(); i++) {
            for (int j = 0; j < firstImage.getHeight(); j++) {
                if (firstImage.getPixel(i, j) != secondImage.getPixel(i, j)) {

                    bmp.setPixel(i, j, Color.YELLOW);

                    pixels.add(bmp.getPixel(i,j));
                }else{

                    bmp.setPixel(i, j, Color.GRAY);
                }
            }
        }



       return bmp;

       // imgOutput.setImageBitmap(bmp);
    }

}
