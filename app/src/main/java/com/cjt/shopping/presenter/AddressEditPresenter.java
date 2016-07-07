package com.cjt.shopping.presenter;

import android.util.Log;

import com.cjt.shopping.bean.AddAddressBean;
import com.cjt.shopping.bean.UserInfo;
import com.cjt.shopping.model.AddressEditModelImpl;
import com.cjt.shopping.model.Imodel.AddressEditModel;
import com.cjt.shopping.model.Imodel.LoginModel;
import com.cjt.shopping.model.LoginModelImpl;
import com.cjt.shopping.ui.acitivity.AddressEditActivity;

import retrofit2.Call;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 作者: 陈嘉桐 on 2016/7/6
 * 邮箱: 445263848@qq.com.
 */
public class AddressEditPresenter extends BasePresenter<AddressEditActivity>{

    private AddressEditModel mAddressEditModel;

    public AddressEditPresenter() {
        this.mAddressEditModel = AddressEditModelImpl.getInstance();
    }
    //添加地址
    public void addAddress(String name, String phone, String address ,String userId){
        if (mAddressEditModel!=null) {
            mAddressEditModel.addAddress(name,phone,address,userId)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<AddAddressBean>() {
                        @Override
                        public void call(AddAddressBean addAddressBean) {
                            Log.i("CJT","Success"+addAddressBean.getConsignee());
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
