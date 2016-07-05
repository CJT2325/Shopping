package com.cjt.shopping.ui.view;

import com.cjt.shopping.bean.UserInfo;

/**
 * 作者: 陈嘉桐 on 2016/7/4
 * 邮箱: 445263848@qq.com.
 */
public interface LoginView {
    public void showProgress();
    public void hideProgress();
    public void logigSuccess(UserInfo.UserBean userBean);
    public void loginFail();
}
