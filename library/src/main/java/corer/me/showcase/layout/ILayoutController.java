package corer.me.showcase.layout;

import android.view.View;
import android.widget.FrameLayout;

import corer.me.showcase.shape.IShape;
import corer.me.showcase.target.ITarget;

/**
 * Created by corer.zhang on 16/4/30.
 */
public interface ILayoutController {


    void layout(FrameLayout parent, ITarget target, IShape shape);


    View getGuideView();

}
