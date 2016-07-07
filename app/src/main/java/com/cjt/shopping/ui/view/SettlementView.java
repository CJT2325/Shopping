package com.cjt.shopping.ui.view;

import com.cjt.shopping.bean.ShopCartList;

import java.util.List;

/**
 * 作者: 陈嘉桐 on 2016/7/6
 * 邮箱: 445263848@qq.com.
 */
public interface SettlementView {
    public void subCartSuccess();
    public void subCartFail();
    public void getShopCartSuccess(List<ShopCartList.ShopCartBean.ShopCartItemsBean> shopCartItems,double totalPrice);
}
