package com.yunfei.explore.ui.template;

import android.os.Bundle;

import com.yunfei.explore.R;
import com.yunfei.explore.base.MVPBaseActivity;

public class TemplateActivity extends MVPBaseActivity<TemplateContact.IView, TemplateContact.IPresenter> implements TemplateContact.IView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected TemplateContact.IPresenter createPresenter() {
        return new TemplatePresenter();
    }
}
