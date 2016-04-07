package com.example.administrator.navigationactivity.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Outline;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.navigationactivity.R;

import static android.view.View.OnTouchListener;


/**
 * Created by Administrator on 15-5-13.
 */
public abstract class BaseActivity<V extends BaseView> extends Activity implements OnTouchListener {
    protected V view;
    /**
     * 上一个activity
     **/
    public static Bitmap prevActivityShotBitmap;

    /**
     * 是否使用右滑返回
     **/
    public static boolean userRightSlide = false;

    public DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            view = getVClass().newInstance();
            view.init(getLayoutInflater(), null);
            if (userRightSlide && prevActivityShotBitmap != null) {
                initDrawer();
            } else {
                setContentView(view.getView());
            }
            onBindV();
            setViewDataHelp();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        onDestroyV();
        view.onDestroy();
        ;
        view = null;
        super.onDestroy();
    }

    /**
     * 获取需要展示的view
     *
     * @return
     */
    protected abstract Class<V> getVClass();

    /**
     * 设置数据处理方法
     */
    protected abstract void setViewDataHelp();

    protected void onBindV() {
    }


    protected void onDestroyV() {
    }


    public interface DataHelper {
        public void sendAction(int tag, Object data);
    }


    @Override
    protected void onStop() {
        super.onStop();
        view.onStop();
    }

    @Override
    public void startActivity(Intent intent) {
        BaseActivity.prevActivityShotBitmap = getActivityShotBitmap();
        super.startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        view.onRestart();
    }


    /***
     * 获取当前窗口的截图
     *
     * @return
     */
    public Bitmap getActivityShotBitmap() {
        View view = getWindow().getDecorView();
        view.buildDrawingCache();
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        int statusBarHeights = rect.top;
        Display display = getWindowManager().getDefaultDisplay();
        int widths = display.getWidth();
        int heights = display.getHeight();
        view.setDrawingCacheEnabled(true);
        Bitmap bmp = Bitmap.createBitmap(view.getDrawingCache(), 0,
                statusBarHeights, widths, heights - statusBarHeights);
        view.destroyDrawingCache();
        return bmp;

    }

    /**
     * 加载抽屉
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void initDrawer() {
        //  drawerLayout = new MyDrawerLayout(this);
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setImageBitmap(prevActivityShotBitmap);
        MyDrawerLayout drawerLayout = new MyDrawerLayout(this);
        drawerLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        drawerLayout.addView(imageView);
        View rightView = view.getView();
        rightView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT));
      //  rightView.setElevation(30.0f);
        rightView.setBackgroundResource(R.drawable.right_view_background);
        drawerLayout.addView(rightView);
        setContentView(drawerLayout);
        drawerLayout.setDrawerListener(new MyDrawerLayout.DrawerListener() {
            @Override
            public void close() {
                finish();
            }
        });

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN && motionEvent.getX() < 20) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
        }
        return super.onTouchEvent(motionEvent);
    }
}
