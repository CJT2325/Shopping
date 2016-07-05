package com.cjt.shopping.model.Imodel;

import com.cjt.shopping.bean.OrderList;

import rx.Observable;

/**
 * 作者: 陈嘉桐 on 2016/7/4
 * 邮箱: 445263848@qq.com.
 */
public interface OrderModel {
    public Observable<OrderList> getOrderList(String userId);
}
