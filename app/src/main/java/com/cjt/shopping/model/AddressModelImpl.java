package com.cjt.shopping.model;

import com.cjt.shopping.bean.AddressList;
import com.cjt.shopping.bean.SubAddress;
import com.cjt.shopping.model.Imodel.AddressEditModel;
import com.cjt.shopping.model.Imodel.AddressModel;
import com.cjt.shopping.model.server.ServerAPI;
import com.cjt.shopping.model.server.ServerAPIModel;

import retrofit2.Call;
import rx.Observable;

/**
 * 作者: 陈嘉桐 on 2016/7/6
 * 邮箱: 445263848@qq.com.
 */
public class AddressModelImpl implements AddressModel {
    private ServerAPI mServerAPI;

    private AddressModelImpl() {
        mServerAPI = ServerAPIModel.provideServerAPI(ServerAPIModel.provvideOkHttpClient());
    }

    public static AddressModel getInstance() {
        return AddressModellHolder.instance;
    }

    private final static class AddressModellHolder {
        private final static AddressModel instance = new AddressModelImpl();
    }
    @Override
    public Observable<AddressList> getAddressList(String userId) {
        return mServerAPI.getAddress(userId);
    }

    @Override
    public Observable<SubAddress> subAddress(String addressId, String userId, String orderId) {
        return mServerAPI.subAddress("subAddress",addressId,userId,orderId);
    }
}
