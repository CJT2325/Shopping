package com.cjt.shopping.model;

import android.util.Log;

import com.cjt.shopping.bean.AddGoodResult;
import com.cjt.shopping.bean.Order;
import com.cjt.shopping.bean.ShopCartList;
import com.cjt.shopping.bean.ShopInfo;
import com.cjt.shopping.model.Imodel.SelectModel;
import com.cjt.shopping.model.Imodel.ShopInfoModel;
import com.cjt.shopping.model.server.ServerAPI;
import com.cjt.shopping.model.server.ServerAPIModel;

import retrofit2.Call;
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

    @Override
    public Observable<AddGoodResult> addGoosToShopCart(String goodId, String userId) {
        return mServerAPI.addGood("addGood", "1", goodId, userId);
    }

    @Override
    public Observable<AddGoodResult> modGoosToShopCart(String count,String goodId, String userId) {
        return  mServerAPI.addGood("mod", count, goodId, userId);
    }

    @Override
    public Observable<Order> addToOrder(String id, String userId) {
        Log.i("CJT",id+" "+userId);
        return mServerAPI.toOrder("add",id, userId);
    }
}
