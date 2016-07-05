package com.cjt.shopping.presenter;

import android.util.Log;

import com.cjt.shopping.bean.OrderList;
import com.cjt.shopping.fragment.OrderFragment;
import com.cjt.shopping.fragment.view.OrderView;
import com.cjt.shopping.model.Imodel.OrderModel;
import com.cjt.shopping.model.OrderModelImpl;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 作者: 陈嘉桐 on 2016/7/4
 * 邮箱: 445263848@qq.com.
 */
public class OrderPresenter extends BasePresenter<OrderFragment>{
    private OrderModel mOrderModel;
    private OrderView mOrderView;

    public OrderPresenter(OrderView orderView) {
        this.mOrderModel = OrderModelImpl.getInstance();
        this.mOrderView=orderView;
    }

    //登录
    public void getOrderList(String userId){
        if (mOrderModel!=null) {
            mOrderModel.getOrderList(userId)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<OrderList>() {
                        @Override
                        public void call(OrderList orderList) {
                            if (orderList.getOrders()!=null){
                                mOrderView.updatas(orderList.getOrders());
                            }
                            Log.i("CJT", "订单数量："+orderList.getOrders().size());
//                            mOrderView
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            Log.i("RxJava", "又是在这里出现了问题呀----->" + throwable.toString());
                        }
                    });
        }else{
            Log.i("CJT","model is null");
        }
    }
}
