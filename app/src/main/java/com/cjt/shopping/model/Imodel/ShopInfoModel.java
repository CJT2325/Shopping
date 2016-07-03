package com.cjt.shopping.model.Imodel;

import com.cjt.shopping.bean.ShopInfo;
import com.cjt.shopping.bean.ShopList;

import rx.Observable;

/**
 * 作者: 陈嘉桐 on 2016/7/3
 * 邮箱: 445263848@qq.com.
 */
public interface ShopInfoModel {
    public Observable<ShopInfo> getShopInfo(String vendorId);
}
