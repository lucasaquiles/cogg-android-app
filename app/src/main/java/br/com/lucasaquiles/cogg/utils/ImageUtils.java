package br.com.lucasaquiles.cogg.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by lucasaquiles on 12/16/15.
 */
public class ImageUtils {
    private static final int threashold = 0;
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


    public static int getResourceFromDrawable(Context context, ImageView imageView){

        String name = context.getResources().getResourceName(imageView.getId());

        String openedDrawlable = imageView.getTag().toString();

        int id = context.getResources().getIdentifier(openedDrawlable, "drawable", context.getPackageName());

        return id;
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


    /**
     * retorna o percentual de diferenca entre as duas imagens
     * @param firstImage
     * @param secondImage
     * @return
     */
    public static double calcDiference(Bitmap firstImage, Bitmap secondImage){

        Bitmap bmp = secondImage.copy(secondImage.getConfig(), true);

        long diff = 0;

        int width1 = firstImage.getWidth();
        int width2 = secondImage.getWidth();
        int height1 = firstImage.getHeight();
        int height2 = secondImage.getHeight();

        if (firstImage.getWidth() != secondImage.getWidth()
                || firstImage.getHeight() != secondImage.getHeight()) {
            return 0.0;
        }

        firstImage = convertToGrayScale(firstImage);
        secondImage = convertToGrayScale(secondImage);


        for (int i = 0; i < firstImage.getWidth(); i++) {
            for (int j = 0; j < firstImage.getHeight(); j++) {
                if (firstImage.getPixel(i, j) != secondImage.getPixel(i, j)) {

                 //   bmp.setPixel(i, j, Color.YELLOW);
                }

                int rgb1 = firstImage.getPixel(i, j);
                int rgb2 = secondImage.getPixel(i,j);
                int r1 = (rgb1 >> 16) & 0xff;
                int g1 = (rgb1 >>  8) & 0xff;
                int b1 = (rgb1      ) & 0xff;
                int r2 = (rgb2 >> 16) & 0xff;
                int g2 = (rgb2 >>  8) & 0xff;
                int b2 = (rgb2      ) & 0xff;
                diff += Math.abs(r1 - r2);
                diff += Math.abs(g1 - g2);
                diff += Math.abs(b1 - b2);

            }
        }

        double n = width1 * height1 * 3;
        double p = diff / n / 255.0;

        double percentagem =   (p * 100.0);
        return percentagem;
    }


    public static Bitmap convertToGrayScale(Bitmap bmpOriginal){


        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();

        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);


        return bmpGrayscale;
    }



    public static Bitmap printMatrix(Bitmap firstImage){

        int[] rgbValues = new int[firstImage.getWidth() * firstImage.getHeight()];  ;

        for(int i=0; i < firstImage.getWidth(); i++)
        {
            for(int j=0; j < firstImage.getHeight(); j++)
            {
                //This is a great opportunity to filter the ARGB values
                rgbValues[(j * firstImage.getWidth()) + i] = firstImage.getPixel(i, j);
                 int values =  rgbValues[(j * firstImage.getWidth()) + i];
                int rvalue = Color.red(values);
                Log.i("Pixel Value", "Red pixel: " + rvalue);
                int gvalue = Color.green(values);
                Log.i("Pixel Value", "Green pixel: " + gvalue);
                int bvalue = Color.blue(values);
                Log.i("Pixel Value", "Blue pixel " + bvalue);

            }
        }


        Bitmap bitmap = Bitmap.createBitmap(rgbValues, firstImage.getWidth(), firstImage.getHeight(), Bitmap.Config.ARGB_8888);
        return bitmap;
    }
    /**
     * destaca a diferenca entre duas imagens
     * @param firstImage
     * @param secondImage
     * @return
     */
    public static Bitmap findDifference(Bitmap firstImage, Bitmap secondImage) {
        Bitmap bmp = secondImage.copy(firstImage.getConfig(), true);

        firstImage = convertToGrayScale(firstImage);
        secondImage = convertToGrayScale(secondImage);


        if (firstImage.getWidth() != secondImage.getWidth()
                || firstImage.getHeight() != secondImage.getHeight()) {
            return null;
        }

        Canvas canvas = new Canvas(bmp);
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        p.setAntiAlias(true);
        p.setFilterBitmap(true);
        p.setDither(true);
        p.setColor(Color.RED);
        float bx = (canvas.getWidth() / firstImage.getWidth());
        float by = (canvas.getHeight() / firstImage.getHeight());

        for (int i = 0; i < firstImage.getWidth(); i++) {
            for (int j = 0; j < firstImage.getHeight(); j++) {

                int pixel = firstImage.getPixel(i,j);
                int redValue = Color.red(pixel);
                int blueValue = Color.blue(pixel);
                int greenValue = Color.green(pixel);

                int pixel2 = secondImage.getPixel(i,j);
                int redValue2 = Color.red(pixel2);
                int blueValue2 = Color.blue(pixel2);
                int greenValue2 = Color.green(pixel2);


                if (Math.abs(redValue2 - redValue) + Math.abs(blueValue2 - blueValue) + Math.abs(greenValue2 - greenValue) <= threashold){
                    bmp.setPixel(i, j, Color.TRANSPARENT);


                }else{
                    //gc.drawRect((int)(x * bx), (int)(y * by), (int)bx, (int)by);

                }

                if (firstImage.getPixel(i, j) != secondImage.getPixel(i, j)) {

                }else{

                }
            }
        }

       return bmp;
    }

}
