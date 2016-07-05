package com.cjt.shopping.model;

import com.cjt.shopping.bean.ShopCartList;
import com.cjt.shopping.bean.ShopInfo;
import com.cjt.shopping.model.Imodel.SelectModel;
import com.cjt.shopping.model.Imodel.ShopInfoModel;
import com.cjt.shopping.model.server.ServerAPI;
import com.cjt.shopping.model.server.ServerAPIModel;

import rx.Observable;

/**
 * 作者: 陈嘉桐 on 2016/7/3
 * 邮箱: 445263848@qq.com.
 */
public class SelectModelImpl implements SelectModel {

    private ServerAPI mServerAPI;

    private SelectModelImpl() {
        mServerAPI = ServerAPIModel.provideServerAPI(ServerAPIModel.provvideOkHttpClient());
    }

    public static SelectModel getInstance() {
        return SelectModelHolder.instance;
    }

    private final static class SelectModelHolder {
        private final static SelectModel instance = new SelectModelImpl();
    }


    @Override
    public Observable<ShopInfo> getSelect(String vendorId) {
        return mServerAPI.shopInfo(vendorId);
    }

    @Override
    public Observable<ShopCartList> getShopCart(String userId, String storeId) {
        return mServerAPI.shopCart(userId, storeId);
    }
}
