package corer.me.showcase;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import corer.me.showcase.animation.AnimationController;
import corer.me.showcase.animation.IAnimationController;
import corer.me.showcase.layout.ILayoutController;
import corer.me.showcase.layout.LayoutController;
import corer.me.showcase.listener.ShowCaseListener;
import corer.me.showcase.shape.CircleShape;
import corer.me.showcase.shape.IShape;
import corer.me.showcase.target.ITarget;
import corer.me.showcase.target.ViewTarget;

/**
 * Created by corer.zhang on 16/4/30.
 */
public class ShowCaseView extends FrameLayout implements View.OnTouchListener {

    IShape mShape;
    ITarget mTarget;
    ILayoutController mILayout;
    IAnimationController mAnimation;

    boolean shouldRender;
    Bitmap mBitmap;
    Canvas mCanvas;
    int mOldHeight;
    int mOldWidth;

    GlobalLayoutListener mGlobalLayoutListener;
    List<ShowCaseListener> mListener;


    public ShowCaseView(Context context) {
        super(context);
        init();
    }

    public ShowCaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShowCaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ShowCaseView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init() {
        setWillNotDraw(false);
        setVisibility(INVISIBLE);
        setOnTouchListener(this);
        mGlobalLayoutListener = new GlobalLayoutListener();
        getViewTreeObserver().addOnGlobalLayoutListener(mGlobalLayoutListener);
        mListener = new ArrayList<>();
    }


    public void show(Activity activity) {
        showInner(activity);
    }

    public void hide() {
        hideInner();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!shouldRender) {
            return;
        }

        final int width = getMeasuredWidth();
        final int height = getMeasuredHeight();

        if (width <= 0 || height <= 0) {
            return;
        }

        if (mBitmap == null || mCanvas == null || mOldHeight != height || mOldWidth != width) {
            if (mBitmap != null) mBitmap.recycle();
            mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
        }

        mOldWidth = width;
        mOldHeight = height;

        mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        if (mShape != null) {
            mShape.draw(mCanvas, mTarget);
        }

        applyLayout();

        canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    protected class GlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        @Override
        public void onGlobalLayout() {
            applyLayout();
        }
    }

    protected void applyLayout() {
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) getLayoutParams();
        int navHeight = getSoftButtonsBarSizePort((Activity) getContext());
        if (lp != null && lp.bottomMargin != navHeight) {
            lp.bottomMargin = navHeight;
        }

        if (mILayout != null && mShape != null && mTarget != null) {
            mILayout.layout(ShowCaseView.this, mTarget, mShape);
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        hideInner();
        return true;
    }


    protected void notifyDisplayed() {
        if (mListener != null) {
            for (ShowCaseListener showCaseListener : mListener) {
                showCaseListener.onShowCaseDisplayed(this);
            }
        }
    }

    protected void notifyDismissed() {
        if (mListener != null) {
            for (ShowCaseListener showCaseListener : mListener) {
                showCaseListener.onShowCaseDismissed(this);
            }
        }
    }

    protected void addShowCaseListener(ShowCaseListener listener) {
        if (mListener != null) {
            mListener.add(listener);
        }
    }

    protected void clearListener() {
        if (mListener != null) {
            mListener.clear();
        }
    }


    protected void setAnimationController(IAnimationController mAnimation) {
        this.mAnimation = mAnimation;
    }

    protected void setLayoutController(ILayoutController layoutController) {
        this.mILayout = layoutController;
        View guideView = layoutController.getGuideView();
        if (guideView.getParent() != null && guideView.getParent() instanceof ViewGroup) {
            ((ViewGroup) guideView.getParent()).removeView(guideView);
        }
        addView(layoutController.getGuideView(), new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    protected void setTarget(View target) {
        this.mTarget = new ViewTarget(target);
    }

    protected void setShape(IShape mShape) {
        this.mShape = mShape;
    }


    protected void showInner(Activity activity) {
        shouldRender = true;

        if (getParent() != null && getParent() instanceof ViewGroup) {
            ((ViewGroup) getParent()).removeView(this);
        }

        ((ViewGroup) activity.getWindow().getDecorView()).addView(this);
        setVisibility(INVISIBLE);

        if (mAnimation != null) {
            if (mAnimation.getAnimateState() != IAnimationController.STATE_ANIMATE_STOP) {
                return;
            }

            postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAnimation.showAnimation(ShowCaseView.this, new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            notifyDisplayed();
                            setVisibility(VISIBLE);
                        }
                    });
                }
            }, 100);
        } else {
            notifyDisplayed();
            setVisibility(VISIBLE);
        }

    }


    protected void hideInner() {
        shouldRender = false;
        if (mAnimation != null) {

            if (mAnimation.getAnimateState() != IAnimationController.STATE_ANIMATE_STOP) {
                return;
            }

            mAnimation.hideAnimation(this, new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {

                    setVisibility(INVISIBLE);
                    removeFromWindow();
                    clearListener();
                }
            });
        } else {

            setVisibility(INVISIBLE);
            removeFromWindow();
            clearListener();
        }
    }

    protected void removeFromWindow() {

        if (getParent() != null && getParent() instanceof ViewGroup) {
            ((ViewGroup) getParent()).removeView(this);
        }
        notifyDismissed();

        if (mGlobalLayoutListener != null) {
            getViewTreeObserver().removeGlobalOnLayoutListener(mGlobalLayoutListener);
            mGlobalLayoutListener = null;
        }


        if (mBitmap != null) {
            mBitmap.recycle();
            mBitmap = null;
        }
    }

    public static int getSoftButtonsBarSizePort(Activity activity) {
        // getRealMetrics is only available with API 17 and +
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            DisplayMetrics metrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int usableHeight = metrics.heightPixels;
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
            int realHeight = metrics.heightPixels;
            if (realHeight > usableHeight)
                return realHeight - usableHeight;
            else
                return 0;
        }
        return 0;
    }


    public static class Builder {
        ShowCaseView showCaseView;
        View guideView;
        ILayoutController layoutController;
        IAnimationController animationController;
        IShape shape;

        public Builder(Context context) {
            showCaseView = new ShowCaseView(context);
        }

        public Builder addShowCaseListener(ShowCaseListener listener) {
            showCaseView.addShowCaseListener(listener);
            return this;
        }

        public Builder setGuideView(View guideView) {
            this.guideView = guideView;
            return this;
        }

        public Builder setShape(IShape shape) {
            this.shape = shape;
            return this;
        }

        public Builder setLayoutController(ILayoutController layoutController) {
            this.layoutController = layoutController;
            return this;
        }

        public Builder setAnimationController(IAnimationController animationController) {
            this.animationController = animationController;
            return this;
        }

        public ShowCaseView build(View targetView) {

            if (layoutController == null && guideView != null) {
                layoutController = new LayoutController(guideView);
            }

            if (animationController == null) {
                animationController = new AnimationController();
            }

            if (shape == null) {
                shape = new CircleShape();
            }


            showCaseView.setLayoutController(layoutController);
            showCaseView.setAnimationController(animationController);
            showCaseView.setShape(shape);
            showCaseView.setTarget(targetView);

            return showCaseView;
        }


    }

}
