package com.example.administrator.navigationactivity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.administrator.navigationactivity.R;
import com.example.administrator.navigationactivity.base.BaseActivity;
import com.example.administrator.navigationactivity.view.View2;


public class ActivityTwo extends BaseActivity<View2> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected Class getVClass() {
        return View2.class;
    }

    @Override
    protected void setViewDataHelp() {
        view.setDataHelp(dataHelper);

    }

    DataHelper dataHelper = new DataHelper() {
        @Override
        public void sendAction(int tag, Object data) {
            switch (tag){
                case 1:
                    Intent intent = new Intent(ActivityTwo.this,MainActivity.class);
                    startActivity(intent);
                    break;
            }

        }
    };
}
