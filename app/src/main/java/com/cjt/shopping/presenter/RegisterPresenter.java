package com.cjt.shopping.presenter;

import android.util.Log;

import com.cjt.shopping.bean.UserInfo;
import com.cjt.shopping.model.Imodel.LoginModel;
import com.cjt.shopping.model.LoginModelImpl;
import com.cjt.shopping.ui.acitivity.LoginActivity;
import com.cjt.shopping.ui.acitivity.RegisterActivity;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 作者: 陈嘉桐 on 2016/7/4
 * 邮箱: 445263848@qq.com.
 */
public class RegisterPresenter extends BasePresenter<RegisterActivity>{

    private LoginModel mLoginModel;

    public RegisterPresenter() {
        this.mLoginModel = LoginModelImpl.getInstance();
    }
    //登录
    public void login(String phone,String password){
        if (mLoginModel!=null) {
            mLoginModel.Login(phone,password)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<UserInfo>() {
                        @Override
                        public void call(UserInfo userinfo) {
//                            Log.i("CJT", "xixixixixi"+userinfo.getUser().getName());
                            if (userinfo.getUser()!=null) {
//                                getView().logigSuccess(userinfo.getUser());
                            }else{
//                                getView().loginFail();
                            }
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            Log.i("RxJava", "又是在这里出现了问题呀----->" + throwable.toString());
                        }
                    });
        }else{
            Log.i("CJT","model is null");
        }
    }
}
