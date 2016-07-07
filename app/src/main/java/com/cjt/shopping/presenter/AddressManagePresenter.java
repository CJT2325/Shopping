package com.cjt.shopping.presenter;

import android.util.Log;

import com.cjt.shopping.bean.AddAddressBean;
import com.cjt.shopping.bean.AddressList;
import com.cjt.shopping.bean.SubAddress;
import com.cjt.shopping.model.AddressEditModelImpl;
import com.cjt.shopping.model.AddressModelImpl;
import com.cjt.shopping.model.Imodel.AddressEditModel;
import com.cjt.shopping.model.Imodel.AddressModel;
import com.cjt.shopping.ui.acitivity.AddressManageActivity;
import com.cjt.shopping.ui.acitivity.BaseActivity;

import retrofit2.Call;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 作者: 陈嘉桐 on 2016/7/6
 * 邮箱: 445263848@qq.com.
 */
public class AddressManagePresenter extends BasePresenter<AddressManageActivity>{
    private AddressModel mAddressModel;

    public AddressManagePresenter() {
        this.mAddressModel = AddressModelImpl.getInstance();
    }
    //添加地址
    public void getAddress(String userId){
        if (mAddressModel!=null) {
            mAddressModel.getAddressList(userId)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<AddressList>() {
                        @Override
                        public void call(AddressList addressList) {
                            Log.i("CJT","Success "+addressList.getUser().getMyAddresses().size());
                            getView().getAddressSuccess(addressList.getUser().getMyAddresses());
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

    //设置收货地址
    public void subAddress(String AddressId, String userId,String orderId) {
        if (mAddressModel!=null) {
            mAddressModel.subAddress(AddressId,userId,orderId)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<SubAddress>() {
                        @Override
                        public void call(SubAddress subAddress) {
                            Log.i("CJT","Success "+subAddress.getUser().getName());
                            getView().subAddressSuccess();
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
