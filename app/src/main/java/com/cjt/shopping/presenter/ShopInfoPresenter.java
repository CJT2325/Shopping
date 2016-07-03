package com.cjt.shopping.presenter;

import android.util.Log;

import com.cjt.shopping.bean.ShopInfo;
import com.cjt.shopping.bean.ShopList;
import com.cjt.shopping.fragment.ShopInfoFragment;
import com.cjt.shopping.fragment.view.HomeView;
import com.cjt.shopping.fragment.view.ShopInfoView;
import com.cjt.shopping.model.HomeModelImpl;
import com.cjt.shopping.model.Imodel.HomeModel;
import com.cjt.shopping.model.Imodel.ShopInfoModel;
import com.cjt.shopping.model.ShopInfoModelImpl;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 作者: 陈嘉桐 on 2016/7/3
 * 邮箱: 445263848@qq.com.
 */
public class ShopInfoPresenter extends BasePresenter<ShopInfoFragment>{
    private ShopInfoModel mShopInfoModel;
    private ShopInfoView mShopInfoView;

    public ShopInfoPresenter(ShopInfoView mShopInfoView) {
        this.mShopInfoModel = ShopInfoModelImpl.getInstance();
        this.mShopInfoView=mShopInfoView;
    }

    //根据商家ID获取商家信息
    public void getShopInfo(String vendorId){
        if (mShopInfoModel!=null) {
            mShopInfoModel.getShopInfo(vendorId)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<ShopInfo>() {
                        @Override
                        public void call(ShopInfo shopInfo) {
                            Log.i("CJT", shopInfo.getCategories().size()+" ");
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
