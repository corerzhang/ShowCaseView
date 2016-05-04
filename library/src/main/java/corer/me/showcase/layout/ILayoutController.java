package corer.me.showcase.layout;

import android.view.View;
import android.widget.FrameLayout;

import corer.me.showcase.ShowCaseView;
import corer.me.showcase.shape.IShape;
import corer.me.showcase.target.ITarget;

/**
 *
 * Layout your own guide view
 *
 *
 * Created by corer.zhang on 16/4/30.
 *
 */
public interface ILayoutController {


    /**
     * layout guide view on showcase view
     * @param showCaseView
     * @param target
     * @param shape
     */
    void layout(ShowCaseView showCaseView, ITarget target, IShape shape);

    /**
     * add guide view on showcase view
     * @param showCaseView
     */
    void addView(ShowCaseView showCaseView);

}
