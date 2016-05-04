package corer.me.showcase.shape;

import android.graphics.Canvas;

import corer.me.showcase.target.ITarget;


/**
 * Draw the high light shape
 *
 * Created by corer.zhang on 16/4/30.
 * 
 */
public interface IShape {


    /**
     * draw the high light shape
     * @param canvas
     * @param target
     */
    void draw(Canvas canvas, ITarget target);

    int getWidth();

    int getHeight();
}
