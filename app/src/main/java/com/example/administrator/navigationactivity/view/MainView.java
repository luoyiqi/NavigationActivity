package com.example.administrator.navigationactivity.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.navigationactivity.R;
import com.example.administrator.navigationactivity.base.BaseActivity;
import com.example.administrator.navigationactivity.base.BaseView;

/**
 * Created by Administrator on 16-1-25.
 */
public class MainView implements BaseView,View.OnClickListener{
    private View view;
    private Button start;
    private BaseActivity.DataHelper dataHelper;
    @Override
    public View getView() {
        return view;
    }

    @Override
    public void init(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.activity_main,viewGroup,false);
        start = (Button) view.findViewById(R.id.start);
        start.setOnClickListener(this);
    }

    @Override
    public void setDataHelp(BaseActivity.DataHelper dataHelper) {
        this.dataHelper = dataHelper;

    }

    @Override
    public void onDataChange(int tag, Object data) {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onClick(View view) {
        dataHelper.sendAction(1,null);
    }
}
