package com.cjt.shopping.model.Imodel;

import com.cjt.shopping.bean.AddressList;
import com.cjt.shopping.bean.SubAddress;

import retrofit2.Call;
import rx.Observable;

/**
 * 作者: 陈嘉桐 on 2016/7/6
 * 邮箱: 445263848@qq.com.
 */
public interface AddressModel {
    public Observable<AddressList> getAddressList(String userId);

    public Observable<SubAddress> subAddress(String addressId, String userId, String orderId);
}
