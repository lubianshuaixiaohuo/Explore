package com.yunfei.explore.ui.main;

import android.os.Bundle;

import com.yunfei.explore.R;
import com.yunfei.explore.base.MVPBaseActivity;

public class MainActivity extends MVPBaseActivity<MainContact.IView,MainContact.IPresenter> implements MainContact.IView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected MainContact.IPresenter createPresenter() {
        return new MainPresenter();
    }
}
