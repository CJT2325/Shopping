package com.cjt.shopping.fragment.view;

import com.cjt.shopping.bean.ShopCartList;
import com.cjt.shopping.bean.ShopInfo;

import java.util.List;

/**
 * 作者: 陈嘉桐 on 2016/7/3
 * 邮箱: 445263848@qq.com.
 */
public interface SelectView {
    public void updata(List<ShopInfo.CategoriesBean> categoriesList,List<ShopInfo.GoodsBean> goodsList);

    public void updataShopCart(List<ShopCartList.ShopCartBean.ShopCartItemsBean> shopCartItems);

    public void addGoodSuccess();

    public void addGoodFail();

    public void updataTotalPrice(double price);

    public void goToSettlement(int orderId);
}
