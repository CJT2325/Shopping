package com.cjt.shopping.model;

import com.cjt.shopping.bean.OrderList;
import com.cjt.shopping.model.Imodel.OrderModel;
import com.cjt.shopping.model.server.ServerAPI;
import com.cjt.shopping.model.server.ServerAPIModel;

import rx.Observable;

/**
 * 作者: 陈嘉桐 on 2016/7/4
 * 邮箱: 445263848@qq.com.
 */
public class OrderModelImpl implements OrderModel {

    private ServerAPI mServerAPI;

    private OrderModelImpl() {
        mServerAPI = ServerAPIModel.provideServerAPI(ServerAPIModel.provvideOkHttpClient());
    }

    public static OrderModel getInstance() {
        return OrderModelHolder.instance;
    }

    private final static class OrderModelHolder {
        private final static OrderModel instance = new OrderModelImpl();
    }

    @Override
    public Observable<OrderList> getOrderList(String userId) {
        return mServerAPI.orderList(userId);
    }


}
