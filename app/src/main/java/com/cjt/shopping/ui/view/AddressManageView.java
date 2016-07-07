package com.cjt.shopping.ui.view;

import com.cjt.shopping.bean.AddressList;

import java.util.List;

/**
 * 作者: 陈嘉桐 on 2016/7/6
 * 邮箱: 445263848@qq.com.
 */
public interface AddressManageView {
    public void getAddressSuccess(List<AddressList.UserBean.MyAddressesBean> myAddresses);
    public void getAddressFail();

    public void subAddressSuccess();
}
