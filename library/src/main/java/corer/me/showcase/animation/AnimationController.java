package corer.me.showcase.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by corer.zhang on 16/4/30.
 */
public class AnimationController implements IAnimationController {

    int state = IAnimationController.STATE_ANIMATE_STOP;

    @Override
    public void showAnimation(View showcase, Animator.AnimatorListener listener) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(showcase, "alpha", 0, 1);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(200);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                state= IAnimationController.STATE_ANIMATE_IN;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                state= IAnimationController.STATE_ANIMATE_STOP;
            }
        });
        animator.addListener(listener);
        animator.start();
    }

    @Override
    public void hideAnimation(View showcase, Animator.AnimatorListener listener) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(showcase, "alpha", 1, 0);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.setDuration(100);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                state= IAnimationController.STATE_ANIMATE_OUT;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                state= IAnimationController.STATE_ANIMATE_STOP;
            }
        });
        animator.addListener(listener);
        animator.start();
    }

    @Override
    public int getAnimateState() {
        return state;
    }
}
