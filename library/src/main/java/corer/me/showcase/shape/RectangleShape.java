package corer.me.showcase.shape;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;

import corer.me.showcase.target.ITarget;

/**
 * Created by corer.zhang on 16/4/30.
 */
public class RectangleShape implements IShape {
    private static final int RECT_PADDING = 20;
    private static final int OUTER_PADDING = 40;
    private static final int LINE_WIDTH = 5;
    private static final int LINE_LENGTH = 40;

    Paint mPaintRect;
    Paint mPaintOuter;

    int height;
    int width;

    public RectangleShape() {
        mPaintRect = new Paint();
        mPaintRect.setColor(0xFFFFFFFF);
        mPaintRect.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        mPaintRect.setFlags(Paint.ANTI_ALIAS_FLAG);

        mPaintOuter =new Paint();
        mPaintOuter.setColor(0xFFFF0000);
        mPaintOuter.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaintOuter.setStrokeWidth(LINE_WIDTH);
    }

    @Override
    public void draw(Canvas canvas, ITarget target) {
        if (canvas == null || target == null) {
            return;
        }

        canvas.drawColor(Color.parseColor("#CC000000"));


        Rect bounds = target.getBounds();

        Rect newRect=new Rect();
        newRect.set(bounds.left-RECT_PADDING,bounds.top-RECT_PADDING,bounds.right+RECT_PADDING,bounds.bottom+RECT_PADDING);
        canvas.drawRect(newRect,mPaintRect);




        canvas.drawLine(bounds.left-OUTER_PADDING,bounds.top-OUTER_PADDING,bounds.left-OUTER_PADDING+ LINE_LENGTH,bounds.top-OUTER_PADDING,mPaintOuter);
        canvas.drawLine(bounds.left-OUTER_PADDING,bounds.bottom+OUTER_PADDING,bounds.left-OUTER_PADDING+ LINE_LENGTH,bounds.bottom+OUTER_PADDING,mPaintOuter);
        canvas.drawLine(bounds.right+OUTER_PADDING,bounds.top-OUTER_PADDING,bounds.right+OUTER_PADDING- LINE_LENGTH,bounds.top-OUTER_PADDING,mPaintOuter);
        canvas.drawLine(bounds.right+OUTER_PADDING,bounds.bottom+OUTER_PADDING,bounds.right+OUTER_PADDING- LINE_LENGTH,bounds.bottom+OUTER_PADDING,mPaintOuter);

        canvas.drawLine(bounds.left-OUTER_PADDING,bounds.top-OUTER_PADDING,bounds.left-OUTER_PADDING,bounds.top-OUTER_PADDING+ LINE_LENGTH,mPaintOuter);
        canvas.drawLine(bounds.left-OUTER_PADDING,bounds.bottom+OUTER_PADDING,bounds.left-OUTER_PADDING,bounds.bottom+OUTER_PADDING- LINE_LENGTH,mPaintOuter);

        canvas.drawLine(bounds.right+OUTER_PADDING,bounds.top-OUTER_PADDING,bounds.right+OUTER_PADDING,bounds.top-OUTER_PADDING+ LINE_LENGTH,mPaintOuter);
        canvas.drawLine(bounds.right+OUTER_PADDING,bounds.bottom+OUTER_PADDING,bounds.right+OUTER_PADDING,bounds.bottom+OUTER_PADDING- LINE_LENGTH,mPaintOuter);


        width=bounds.width()+OUTER_PADDING;
        height=bounds.height()+OUTER_PADDING;

    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
