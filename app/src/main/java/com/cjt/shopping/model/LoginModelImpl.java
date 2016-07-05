package com.cjt.shopping.model;

import com.cjt.shopping.bean.UserInfo;
import com.cjt.shopping.model.Imodel.LoginModel;
import com.cjt.shopping.model.Imodel.SelectModel;
import com.cjt.shopping.model.server.ServerAPI;
import com.cjt.shopping.model.server.ServerAPIModel;

import retrofit2.Call;
import rx.Observable;

/**
 * 作者: 陈嘉桐 on 2016/7/4
 * 邮箱: 445263848@qq.com.
 */
public class LoginModelImpl implements LoginModel {

    private ServerAPI mServerAPI;

    private LoginModelImpl() {
        mServerAPI = ServerAPIModel.provideServerAPI(ServerAPIModel.provvideOkHttpClient());
    }

    public static LoginModel getInstance() {
        return LoginModellHolder.instance;
    }

    private final static class LoginModellHolder {
        private final static LoginModel instance = new LoginModelImpl();
    }

    @Override
    public Observable<UserInfo> Login(String phone, String password) {
        return mServerAPI.login("login",phone, password);
    }
}
