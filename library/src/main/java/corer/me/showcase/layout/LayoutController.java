package corer.me.showcase.layout;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import corer.me.showcase.ShowCaseView;
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
    public void addView(ShowCaseView showCaseView) {
        if (showCaseView==null){
            return;
        }
        if (mGuideView == null) {
            return;
        }
        if (mGuideView.getParent() != null && mGuideView.getParent() instanceof ViewGroup) {
            ((ViewGroup) mGuideView.getParent()).removeView(mGuideView);
        }
        showCaseView.addView(mGuideView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public void layout(ShowCaseView showCaseView, ITarget target, IShape shape) {


        int parentHeight = showCaseView.getMeasuredHeight();
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
}
