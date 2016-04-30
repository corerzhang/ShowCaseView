package corer.me.showcase.target;

import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by corer.zhang on 16/4/30.
 */
public class ViewTarget implements ITarget {


    View mView;

    public ViewTarget(View view) {
        this.mView = view;
    }

    @Override
    public Point getPoint() {
        int[] location = new int[2];
        mView.getLocationInWindow(location);
        return new Point(location[0] + mView.getWidth() / 2, location[1] + mView.getHeight() / 2);
    }

    @Override
    public Rect getBounds() {
        int[] location = new int[2];
        mView.getLocationInWindow(location);
        return new Rect(location[0], location[1], location[0] + mView.getWidth(), location[1] + mView.getHeight());
    }
}
