package com.cjt.shopping.model;

import com.cjt.shopping.bean.ShopList;
import com.cjt.shopping.model.Imodel.HomeModel;
import com.cjt.shopping.model.server.ServerAPI;
import com.cjt.shopping.model.server.ServerAPIModel;

import retrofit2.Call;
import rx.Observable;

/**
 * 作者: 陈嘉桐 on 2016/7/3
 * 邮箱: 445263848@qq.com.
 */
public class HomeModelImpl implements HomeModel {
    private ServerAPI mServerAPI;

    private HomeModelImpl() {
        mServerAPI = ServerAPIModel.provideServerAPI(ServerAPIModel.provvideOkHttpClient());
    }

    public static HomeModel getInstance() {
        return HomeModelHolder.instance;
    }

    private final static class HomeModelHolder {
        private final static HomeModel instance = new HomeModelImpl();
    }

    @Override
    public Observable<ShopList> getShopList() {
        return mServerAPI.cityList();
    }
}
