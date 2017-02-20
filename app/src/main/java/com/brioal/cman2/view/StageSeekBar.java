package com.brioal.cman2.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Github : https://github.com/Brioal
 * Email : brioal@foxmial.com
 * Created by Brioal on 2017/2/20.
 */

public class StageSeekBar extends RelativeLayout {
    private View mFrontView;
    private int mStageCount = 4;//阶段数量
    private ViewDragHelper mViewDragHelper;

    public StageSeekBar(@NonNull Context context) {
        this(context, null);
    }

    public StageSeekBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViewDragHelper();
    }

    private void initViewDragHelper() {
        mViewDragHelper = ViewDragHelper.create(this, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child == mFrontView;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return 0;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                if (left < 0) {
                    return 0;
                }
                if (left > getWidth() - mFrontView.getMeasuredWidth()) {
                    return getWidth() - mFrontView.getMeasuredWidth();
                }
                return left;
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                moveto(getShouldPositon(mFrontView.getLeft()));
            }
        });
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mFrontView = getChildAt(1);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private void moveto(int position) {
        if (position + 1 > mStageCount) {
            return;
        }
        float singleIndex = (getWidth() - mStageCount * mFrontView.getMeasuredWidth()) / (mStageCount - 1);
        int left = (int) (position * (mFrontView.getMeasuredWidth() + singleIndex));
        mViewDragHelper.smoothSlideViewTo(mFrontView, left, 0);
        ViewCompat.postInvalidateOnAnimation(this);
    }

    private int getShouldPositon(int left) {
        float singleIndex = (getWidth() - mStageCount * mFrontView.getMeasuredWidth()) / (mStageCount - 1);
        float p = left / (mFrontView.getMeasuredWidth() + singleIndex);
        return (int) (p + 0.5f);
    }
}
