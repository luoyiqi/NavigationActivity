package com.example.administrator.navigationactivity.base;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 16-1-25.
 */
public class MyDrawerLayout extends RelativeLayout {

    private ViewDragHelper mDragHelper;
    private DrawerListener drawerListener;

    boolean isSide = false;

    public MyDrawerLayout(Context context) {
        super(context);
        this.mDragHelper = ViewDragHelper.create(this, 1.0f, new DragHelperCallback());
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    public MyDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mDragHelper = ViewDragHelper.create(this, 1.0f, new DragHelperCallback());
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    public MyDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mDragHelper = ViewDragHelper.create(this, 1.0f, new DragHelperCallback());
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    private class DragHelperCallback extends ViewDragHelper.Callback {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return false;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (left > 0) {
                if (left < getChildAt(0).getWidth() / 3) {
                    if (left < getChildAt(1).getLeft()) {
                        getChildAt(0).layout(getChildAt(0).getLeft() + (getChildAt(1).getLeft() - left),
                                0, getChildAt(0).getLeft() + (getChildAt(1).getLeft() - left)
                                        + getChildAt(0).getWidth(), getChildAt(0).getHeight());
                    } else {
                        getChildAt(0).layout(0 - getChildAt(0).getWidth() +
                                getChildAt(0).getWidth() / 3, 0, getChildAt(0).getWidth() / 3
                                , getChildAt(0).getHeight());
                    }
                } else {
                    getChildAt(0).layout(0 - getChildAt(0).getWidth() + left
                            , 0, left, getChildAt(0).getHeight());
                }

                return left;
            }
            return 0;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            if (releasedChild == getChildAt(1)) {
                if (getChildAt(1).getLeft() < getChildAt(1).getWidth() / 2) {
                    getChildAt(1).layout(0, 0, getChildAt(1).getWidth(), getChildAt(1).getHeight());
                } else {
                    getChildAt(1).layout(getChildAt(1).getWidth(), 0, getChildAt(1).getWidth() * 2, getChildAt(1).getHeight());
                    getChildAt(0).layout(0, 0, getChildAt(0).getWidth(), getChildAt(0).getHeight());
                    if (drawerListener != null) {
                        drawerListener.close();
                    }
                }
            }
        }

        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            mDragHelper.captureChildView(getChildAt(1), pointerId);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (ev.getX() < 30) {
                isSide = true;
            } else {
                isSide = false;
            }
        }
        mDragHelper.processTouchEvent(ev);
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mDragHelper.cancel();
            return false;
        }
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    public void setDrawerListener(DrawerListener drawerListener) {
        this.drawerListener = drawerListener;
    }

    public interface DrawerListener {
        public void close();
    }


}
