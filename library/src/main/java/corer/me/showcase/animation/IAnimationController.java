package corer.me.showcase.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * Created by corer.zhang on 16/4/30.
 */
public interface IAnimationController {


    int STATE_ANIMATE_STOP=0;
    int STATE_ANIMATE_IN = 1;
    int STATE_ANIMATE_OUT = 2;

    void showAnimation(View showcase, Animator.AnimatorListener listener);

    void hideAnimation(View showcase, Animator.AnimatorListener listener);

    int getAnimateState();
}
