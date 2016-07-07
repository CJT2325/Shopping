package com.cjt.shopping.model;

import com.cjt.shopping.bean.AddressList;
import com.cjt.shopping.bean.ShopCartList;
import com.cjt.shopping.bean.SubCart;
import com.cjt.shopping.model.Imodel.AddressModel;
import com.cjt.shopping.model.Imodel.SettlenmentModel;
import com.cjt.shopping.model.server.ServerAPI;
import com.cjt.shopping.model.server.ServerAPIModel;

import rx.Observable;

/**
 * 作者: 陈嘉桐 on 2016/7/6
 * 邮箱: 445263848@qq.com.
 */
public class SettlenmentModelImpl implements SettlenmentModel {
    private ServerAPI mServerAPI;

    private SettlenmentModelImpl() {
        mServerAPI = ServerAPIModel.provideServerAPI(ServerAPIModel.provvideOkHttpClient());
    }

    public static SettlenmentModel getInstance() {
        return SettlenmentModelHolder.instance;
    }


    private final static class SettlenmentModelHolder {
        private final static SettlenmentModel instance = new SettlenmentModelImpl();
    }
    @Override
    public Observable<SubCart> subCart(String orderId, String userId) {
        return mServerAPI.subCart("subCart",orderId,userId);
    }

    @Override
    public Observable<ShopCartList> getShopCart(String userId, String storeId) {
        return mServerAPI.shopCart(userId,storeId);
    }
}
