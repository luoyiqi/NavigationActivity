package com.example.administrator.navigationactivity.base;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by Administrator on 16-1-25.
 */
public class MyDrawerLayout extends DrawerLayout{
    boolean isSide = false;
    public MyDrawerLayout(Context context) {
        super(context);
    }

    public MyDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(ev.getAction() ==MotionEvent.ACTION_DOWN){
            if(ev.getX()<30){
                isSide = true;
            }else{
                isSide = false;
            }
        }
        if(isSide){
            return super.onTouchEvent(ev);
        }
        return false;
    }
}
