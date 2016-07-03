package com.cjt.shopping.presenter;

import android.util.Log;

import com.cjt.shopping.bean.ShopList;
import com.cjt.shopping.fragment.HomeFragment;
import com.cjt.shopping.fragment.view.HomeView;
import com.cjt.shopping.model.HomeModelImpl;
import com.cjt.shopping.model.Imodel.HomeModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 作者: 陈嘉桐 on 2016/7/3
 * 邮箱: 445263848@qq.com.
 */
public class HomePresenter extends BasePresenter<HomeFragment>{
    private HomeModel mHomeModel;
    private HomeView mHomeView;

    public HomePresenter(HomeView mHomeView) {
        this.mHomeModel = HomeModelImpl.getInstance();
        this.mHomeView=mHomeView;
    }

    //查询可用城市列表
    public void getShopList(){
        if (mHomeModel!=null) {
            mHomeModel.getShopList()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<ShopList>() {
                        @Override
                        public void call(ShopList shopList) {
                            Log.i("CJT", shopList.getVendors().get(0).getStore().getName() + " ");
                            mHomeView.updata(shopList.getVendors());
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
