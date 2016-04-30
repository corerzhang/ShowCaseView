package corer.me.showcase.shape;

import android.graphics.Canvas;

import corer.me.showcase.target.ITarget;


/**
 * Created by corer.zhang on 16/4/30.
 */
public interface IShape {


    void draw(Canvas canvas, ITarget target);

    int getWidth();

    int getHeight();
}
