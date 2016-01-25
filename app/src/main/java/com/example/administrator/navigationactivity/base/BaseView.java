package com.example.administrator.navigationactivity.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * 视图接口，所有view的展示、变化、以及用户数据的收集都在这里处理
 */
public interface BaseView {
    public View getView();

    public void init(LayoutInflater layoutInflater, ViewGroup viewGroup);

    /**
     * 设置数据上传的方法
     * @param dataHelp
     */
    public void setDataHelp(BaseActivity.DataHelper dataHelp);

    /**
     * 数据处理完成后的回调
     * @param tag
     * @param data
     */
    public void onDataChange(int tag, Object data);

    public void onDestroy();

    public void onRestart();

    public void onStop();
}
