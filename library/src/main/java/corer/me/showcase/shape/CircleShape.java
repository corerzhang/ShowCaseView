package corer.me.showcase.shape;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;

import corer.me.showcase.target.ITarget;

/**
 * Created by corer.zhang on 16/4/30.
 */
public class CircleShape implements IShape {

    private static final int CIRCLE_PADDING = 20;

    Paint mPaint;
    int mRadius;

    public CircleShape() {
        mPaint = new Paint();
        mPaint.setColor(0xFFFFFFFF);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public void draw(Canvas canvas, ITarget target) {

        if (canvas == null || target == null) {
            return;
        }
        canvas.drawColor(Color.parseColor("#dd335075"));

        Rect bounds = target.getBounds();
        Point point = target.getPoint();
        mRadius = Math.max(bounds.height(), bounds.width()) / 2 + CIRCLE_PADDING;
        canvas.drawCircle(point.x, point.y, mRadius, mPaint);
    }

    @Override
    public int getWidth() {
        return mRadius;
    }

    @Override
    public int getHeight() {
        return mRadius;
    }
}
