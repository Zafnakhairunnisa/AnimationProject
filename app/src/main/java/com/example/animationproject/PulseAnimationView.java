package com.example.animationproject;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

public class PulseAnimationView extends View {

    private float mRadius;
    private final Paint mPaint = new Paint();
    private static final int COLOR_ADJUSTER = 5;
    private float mX, mY;
    private static final int ANIMATION_DURATION = 4000;
    private static final long ANIMATION_DELAY = 1000;
    private AnimatorSet mPulseAnimatorSet = new AnimatorSet();

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //membuat grow animation
        ObjectAnimator growAnimator = ObjectAnimator.ofFloat(this, "radius", 0, getWidth());
        growAnimator.setDuration(ANIMATION_DURATION);
        growAnimator.setInterpolator(new LinearInterpolator());

        //membuat shrink animation
        ObjectAnimator shrinkAnimator = ObjectAnimator.ofFloat(this, "radius", getWidth(), 0);
        shrinkAnimator.setDuration(ANIMATION_DURATION);
        shrinkAnimator.setInterpolator(new LinearInterpolator());
        shrinkAnimator.setStartDelay(ANIMATION_DELAY);

        //membuat repeat animation
        ObjectAnimator repeatAnimatior = ObjectAnimator.ofFloat(this, "radius", 0, getWidth());

        repeatAnimatior.setStartDelay(ANIMATION_DELAY);
        repeatAnimatior.setDuration(ANIMATION_DURATION);
        repeatAnimatior.setRepeatCount(3);
        repeatAnimatior.setRepeatMode(ObjectAnimator.REVERSE);

//        mPulseAnimatorSet.play(growAnimator).before(shrinkAnimator);

        mPulseAnimatorSet.play(repeatAnimatior).after(shrinkAnimator);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mX, mY, mRadius, mPaint);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            mX = event.getX();
            mY = event.getY();

            if (mPulseAnimatorSet != null && mPulseAnimatorSet.isRunning()) {
                mPulseAnimatorSet.cancel();
            }

            mPulseAnimatorSet.start();
        }
        return super.onTouchEvent(event);
    }

    public void setRadius(float radius) {
        mRadius = radius;
        mPaint.setColor(Color.GREEN + (int) radius/COLOR_ADJUSTER);
        invalidate();
    }

    public PulseAnimationView(Context context) {
        super(context);
    }

    public PulseAnimationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
