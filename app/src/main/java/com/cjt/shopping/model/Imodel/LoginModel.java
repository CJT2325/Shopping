package com.cjt.shopping.model.Imodel;
import com.cjt.shopping.bean.UserInfo;

import retrofit2.Call;
import rx.Observable;
/**
 * 作者: 陈嘉桐 on 2016/7/4
 * 邮箱: 445263848@qq.com.
 */
public interface LoginModel {
    public Observable<UserInfo> Login(String phone, String password);
}
