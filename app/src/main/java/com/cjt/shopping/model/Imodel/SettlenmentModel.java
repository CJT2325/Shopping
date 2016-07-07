package com.cjt.shopping.model.Imodel;

import com.cjt.shopping.bean.AddressList;
import com.cjt.shopping.bean.ShopCartList;
import com.cjt.shopping.bean.SubCart;

import rx.Observable;

/**
 * 作者: 陈嘉桐 on 2016/7/6
 * 邮箱: 445263848@qq.com.
 */
public interface SettlenmentModel {
    //结算购物车
    public Observable<SubCart> subCart(String orderId,String userId);
    //获取购物车
    public Observable<ShopCartList> getShopCart(String userId, String storeId);
}
