package com.cjt.shopping.model.Imodel;

import com.cjt.shopping.bean.AddAddressBean;

import retrofit2.Call;
import rx.Observable;

/**
 * 作者: 陈嘉桐 on 2016/7/6
 * 邮箱: 445263848@qq.com.
 */
public interface AddressEditModel {
    public Observable<AddAddressBean> addAddress(String name, String phone, String address, String userId);
}
