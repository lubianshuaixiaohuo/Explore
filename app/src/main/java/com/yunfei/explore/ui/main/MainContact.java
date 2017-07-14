package com.yunfei.explore.ui.main;

import com.yunfei.explore.base.BaseContact;

/**
 * author：zhouyunfei
 * date：2017/7/13 20:21
 * describe：
 */

public interface MainContact {

    /**
     * V视图层
     */
    interface IView extends BaseContact.IBaseView {

    }

    /**
     * P视图与逻辑处理的连接层
     */
    interface IPresenter extends BaseContact.IBasePresenter<IView>{
    }

    /**
     * 逻辑处理层
     */
    interface IModel extends BaseContact.IBaseModle {

    }

}
