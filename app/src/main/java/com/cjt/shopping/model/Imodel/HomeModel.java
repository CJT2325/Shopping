package com.cjt.shopping.model.Imodel;

import com.cjt.shopping.bean.ShopList;

import retrofit2.Call;
import rx.Observable;

/**
 * 作者: 陈嘉桐 on 2016/7/3
 * 邮箱: 445263848@qq.com.
 */
public interface HomeModel {
    //应该要传经纬度到服务器
    public Observable<ShopList> getShopList();
}
