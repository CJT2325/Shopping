package com.cjt.shopping.presenter;

import android.util.Log;

import com.cjt.shopping.bean.AddAddressBean;
import com.cjt.shopping.bean.ShopCartList;
import com.cjt.shopping.bean.SubCart;
import com.cjt.shopping.model.AddressEditModelImpl;
import com.cjt.shopping.model.Imodel.AddressEditModel;
import com.cjt.shopping.model.Imodel.SettlenmentModel;
import com.cjt.shopping.model.SettlenmentModelImpl;
import com.cjt.shopping.ui.acitivity.SettlementActivity;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 作者: 陈嘉桐 on 2016/7/6
 * 邮箱: 445263848@qq.com.
 */
public class SettlementPresenter extends BasePresenter<SettlementActivity>{
    private SettlenmentModel mSettlenmentModel;

    public SettlementPresenter() {
        this.mSettlenmentModel = SettlenmentModelImpl.getInstance();
    }
    //提交订单
    public void subCart(String orderId ,String userId){
        if (mSettlenmentModel!=null) {
            mSettlenmentModel.subCart(orderId,userId)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<SubCart>() {
                        @Override
                        public void call(SubCart subCart) {
                            Log.i("CJT","Success"+subCart.getOrder().getId());
                            getView().subCartSuccess();
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

    public void getShopCart(String userId,String storeId){
        if (mSettlenmentModel!=null) {
            mSettlenmentModel.getShopCart(userId,storeId)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<ShopCartList>() {
                        @Override
                        public void call(ShopCartList shopCartList) {
                            if (shopCartList.getShopCart().getShopCartItems()!=null){
                                Log.i("CJT", "购物车数量"+shopCartList.getShopCart().getShopCartItems().size());
//                                shopCartList.getShopCart().getUser()
                                getView().getShopCartSuccess(shopCartList.getShopCart().getShopCartItems(),shopCartList.getShopCart().getTotalPrice());
                            }
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
