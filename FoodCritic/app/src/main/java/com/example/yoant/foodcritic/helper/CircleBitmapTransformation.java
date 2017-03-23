package com.example.yoant.foodcritic.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

public class CircleBitmapTransformation extends BitmapTransformation {

    public CircleBitmapTransformation(Context context) {
        super(context);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return circleCropImage(pool, toTransform);
    }

    private static Bitmap circleCropImage(BitmapPool pool, Bitmap sourceImage) {
        if (sourceImage == null)
            return null;

        int currentSize = Math.min(sourceImage.getWidth(), sourceImage.getHeight());
        int x = (sourceImage.getWidth() - currentSize) / 2;
        int y = (sourceImage.getHeight() - currentSize) / 2;

        Bitmap squaredImage = Bitmap.createBitmap(sourceImage, x, y, currentSize, currentSize);
        Bitmap resultImage = pool.get(currentSize, currentSize, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(resultImage);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(squaredImage, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        float radius = currentSize / 2f;
        canvas.drawCircle(radius, radius, radius, paint);

        return resultImage;
    }

    @Override
    public String getId() {
        return getClass().getName();
    }
}
