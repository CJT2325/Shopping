package com.cjt.shopping.model.server;

import com.cjt.shopping.bean.AddAddressBean;
import com.cjt.shopping.bean.AddGoodResult;
import com.cjt.shopping.bean.AddressList;
import com.cjt.shopping.bean.Order;
import com.cjt.shopping.bean.OrderList;
import com.cjt.shopping.bean.ShopCartList;
import com.cjt.shopping.bean.ShopInfo;
import com.cjt.shopping.bean.ShopList;
import com.cjt.shopping.bean.SubAddress;
import com.cjt.shopping.bean.SubCart;
import com.cjt.shopping.bean.UserInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 作者: 陈嘉桐 on 2016/7/1
 * 邮箱: 445263848@qq.com.
 */
public interface ServerAPI {
    //网站根目录
    String baseUrl = "http://172.16.101.119:8080/SSC/";

    //获取商家接口
    @GET("user/shop_home.action")
    Observable<ShopList> cityList();

    //通过商家ID获取商家信息
    @GET("user/vendor_good.action")
    Observable<ShopInfo> shopInfo(@Query("vendorId") String vendorId);

    //用户登录
    @GET("user/UserBaseAction.action")
    Observable<UserInfo> login(@Query("action") String action, @Query("name") String name, @Query("password") String password);

    //用户注册


    //根据用户ID获取订单
    @GET("user/order_list.action")
    Observable<OrderList> orderList(@Query("userId") String userId);

    //根据用户ID获取订单
    @GET("user/store_shopcart.action")
    Observable<ShopCartList> shopCart(@Query("userId") String userId, @Query("storeId") String storeId);

    //添加商品到购物车
    @GET("user/shop_cart.action")
    Observable<AddGoodResult> addGood(
            @Query("action") String action,
            @Query("count") String count,
            @Query("id") String goodid,
            @Query("userId") String userId
    );
    //生成订单
    @POST("user/order_item.action")
    Observable<Order> toOrder(
            @Query("action") String action,
            @Query("items") String items,
            @Query("userId") String userId);

    //添加地址
    @POST("user/my_address.action")
    Observable<AddAddressBean> addAddress(
            @Query("action") String action,
            @Query("consignee") String consignee,
            @Query("phone") String phone,
            @Query("path") String path,
            @Query("postalcode") String postalcode,
            @Query("userId") String userId
    );
    //添加地址
    @GET("user/my_address.action")
    Observable<AddressList> getAddress(
            @Query("userId") String userId
    );

    //提交订单
    @GET("user/order_item.action")
    Observable<SubCart> subCart(
            @Query("action") String action,
            @Query("orderId") String orderId,
            @Query("userId") String userId
    );

    //提交收货地址
    @GET("user/order_item.action")
    Observable<SubAddress> subAddress(
            @Query("action") String action,
            @Query("addressId") String addressId,
            @Query("userId") String userId,
            @Query("orderId") String orderId
    );
}
