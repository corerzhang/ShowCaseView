package corer.me.showcase.layout;

import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import corer.me.showcase.shape.IShape;
import corer.me.showcase.target.ITarget;

/**
 * Created by corer.zhang on 16/4/30.
 */
public class LayoutController implements ILayoutController {


    View mGuideView;

    public LayoutController(View guideView) {
        this.mGuideView = guideView;
    }

    @Override
    public void layout(FrameLayout parent, ITarget target, IShape shape) {


        int parentHeight = parent.getMeasuredHeight();
        int midPos = parentHeight / 2;
        int targetY = target.getPoint().y;

        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mGuideView.getLayoutParams();
        int marginTop;
        int marginBottom;
        int gravity;
        if (targetY > midPos) {
            //guide show on top
            gravity = Gravity.BOTTOM;
            marginBottom = parentHeight - targetY + shape.getHeight() / 2;
            marginTop = 0;
        } else {
            //guide show on bottom
            gravity = Gravity.TOP;
            marginBottom = 0;
            marginTop = targetY + shape.getHeight() / 2;
        }

        boolean hasChanged = false;
        if (lp.bottomMargin != marginBottom) {
            lp.bottomMargin = marginBottom;
            hasChanged = true;
        }

        if (lp.topMargin != marginTop) {
            lp.topMargin = marginTop;
            hasChanged = true;
        }

        if (lp.gravity != gravity) {
            lp.gravity = gravity;
            hasChanged = true;
        }


        if (hasChanged) {
            mGuideView.setLayoutParams(lp);

        }


    }

    @Override
    public View getGuideView() {
        return mGuideView;
    }
}
