package com.cjt.shopping.model;

import com.cjt.shopping.bean.ShopInfo;
import com.cjt.shopping.model.Imodel.HomeModel;
import com.cjt.shopping.model.Imodel.ShopInfoModel;
import com.cjt.shopping.model.server.ServerAPI;
import com.cjt.shopping.model.server.ServerAPIModel;

import rx.Observable;

/**
 * 作者: 陈嘉桐 on 2016/7/3
 * 邮箱: 445263848@qq.com.
 */
public class ShopInfoModelImpl implements ShopInfoModel {
    private ServerAPI mServerAPI;

    private ShopInfoModelImpl() {
        mServerAPI = ServerAPIModel.provideServerAPI(ServerAPIModel.provvideOkHttpClient());
    }
    public static ShopInfoModel getInstance() {
        return ShopInfoModelHolder.instance;
    }
    private final static class ShopInfoModelHolder {
        private final static ShopInfoModel instance = new ShopInfoModelImpl();
    }
    @Override
    public Observable<ShopInfo> getShopInfo(String vendorId) {
        return mServerAPI.shopInfo(vendorId);
    }
}
