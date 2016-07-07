package com.cjt.shopping.model;

import com.cjt.shopping.bean.AddAddressBean;
import com.cjt.shopping.bean.UserInfo;
import com.cjt.shopping.model.Imodel.AddressEditModel;
import com.cjt.shopping.model.server.ServerAPI;
import com.cjt.shopping.model.server.ServerAPIModel;

import retrofit2.Call;
import rx.Observable;

/**
 * 作者: 陈嘉桐 on 2016/7/6
 * 邮箱: 445263848@qq.com.
 */
public class AddressEditModelImpl implements AddressEditModel{
    private ServerAPI mServerAPI;

    private AddressEditModelImpl() {
        mServerAPI = ServerAPIModel.provideServerAPI(ServerAPIModel.provvideOkHttpClient());
    }

    public static AddressEditModel getInstance() {
        return AddressEditModellHolder.instance;
    }

    private final static class AddressEditModellHolder {
        private final static AddressEditModel instance = new AddressEditModelImpl();
    }
    @Override
    public Observable<AddAddressBean> addAddress(String name, String phone, String address, String userId) {
        return mServerAPI.addAddress("add",name,phone,address,"0",userId);
    }
}
