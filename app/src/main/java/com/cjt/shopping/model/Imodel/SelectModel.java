package com.cjt.shopping.model.Imodel;

import com.cjt.shopping.bean.AddGoodResult;
import com.cjt.shopping.bean.Order;
import com.cjt.shopping.bean.ShopCartList;
import com.cjt.shopping.bean.ShopInfo;

import retrofit2.Call;
import rx.Observable;

/**
 * 作者: 陈嘉桐 on 2016/7/3
 * 邮箱: 445263848@qq.com.
 */
public interface SelectModel {
    //获取菜单
    public Observable<ShopInfo> getSelect(String vendorId);

    //获取购物车
    public Observable<ShopCartList> getShopCart(String userId, String storeId);

    //添加商品
    public Observable<AddGoodResult> addGoosToShopCart(String goodId, String userId);

    //移出商品
    public Observable<AddGoodResult> modGoosToShopCart(String count,String goodId, String userId);

    //生成订单
    public Observable<Order> addToOrder(String id, String userId);

}
