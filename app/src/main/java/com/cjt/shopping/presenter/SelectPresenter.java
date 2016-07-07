package com.cjt.shopping.presenter;

import android.util.Log;

import com.cjt.shopping.bean.AddGoodResult;
import com.cjt.shopping.bean.Order;
import com.cjt.shopping.bean.ShopCartList;
import com.cjt.shopping.bean.ShopInfo;
import com.cjt.shopping.fragment.SelectFragment;
import com.cjt.shopping.fragment.view.SelectView;
import com.cjt.shopping.fragment.view.ShopInfoView;
import com.cjt.shopping.model.Imodel.SelectModel;
import com.cjt.shopping.model.Imodel.ShopInfoModel;
import com.cjt.shopping.model.SelectModelImpl;
import com.cjt.shopping.model.ShopInfoModelImpl;

import retrofit2.Call;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 作者: 陈嘉桐 on 2016/7/3
 * 邮箱: 445263848@qq.com.
 */
public class SelectPresenter extends BasePresenter<SelectFragment>{
    private SelectModel mSelectModel;
    private SelectView mSelectView;

    public SelectPresenter(SelectView mSelectView) {
        this.mSelectModel = SelectModelImpl.getInstance();
        this.mSelectView=mSelectView;
    }

    //根据商家ID获取商家信息
    public void getSelect(String vendorId){
        if (mSelectModel!=null) {
            mSelectModel.getSelect(vendorId)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<ShopInfo>() {
                        @Override
                        public void call(ShopInfo shopInfo) {
                            Log.i("CJT", "xixixixixi"+shopInfo.getCategories().size()+" "+shopInfo.getGoods().size());
                            mSelectView.updata(shopInfo.getCategories(),shopInfo.getGoods());
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

    //根据商家ID获取商家信息
    public void getShopCart(String userId,String storeId){
        if (mSelectModel!=null) {
            mSelectModel.getShopCart(userId,storeId)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<ShopCartList>() {
                        @Override
                        public void call(ShopCartList shopCartList) {
                            if (shopCartList.getShopCart().getShopCartItems()!=null){
                                Log.i("CJT", "购物车数量"+shopCartList.getShopCart().getShopCartItems().size());
                                mSelectView.updataShopCart(shopCartList.getShopCart().getShopCartItems());
                                mSelectView.updataTotalPrice(shopCartList.getShopCart().getTotalPrice());
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

    //添加商品到购物车
    public void addGoodToShopCart(String goodId,String userId){
        if (mSelectModel!=null) {
            mSelectModel.addGoosToShopCart(goodId,userId)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<AddGoodResult>() {
                        @Override
                        public void call(AddGoodResult mAddGoodResult) {
                            Log.i("CJT","添加成功");
                            mSelectView.addGoodSuccess();
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            Log.i("RxJava", "又是在这里出现了问题呀----->" + throwable.toString());
                            mSelectView.addGoodFail();
                        }
                    });
        }else{
            Log.i("CJT","model is null");
        }
    }

    public void addToOrder(String id, String userId) {
        if (mSelectModel!=null) {
            mSelectModel.addToOrder(id,userId)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Order>() {
                        @Override
                        public void call(Order order) {
                            Log.i("CJT",order.getOrders().get(0).getId()+" ========");
                            mSelectView.goToSettlement(order.getOrders().get(0).getId());
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
