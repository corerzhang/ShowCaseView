package corer.me.showcase.shape;

import android.content.Context;
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


    Paint mPaint;
    int mRadius;
    int padding;

    public CircleShape(Context context) {

        padding =dp2px(context,10);

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
        canvas.drawColor(Color.parseColor("#CC000000"));

        Rect bounds = target.getBounds();
        Point point = target.getPoint();
        mRadius = Math.max(bounds.height(), bounds.width()) / 2 + padding;
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

    private int dp2px(Context context,float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
