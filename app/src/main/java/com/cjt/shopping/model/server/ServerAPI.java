package com.cjt.shopping.model.server;

import com.cjt.shopping.bean.ShopInfo;
import com.cjt.shopping.bean.ShopList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 作者: 陈嘉桐 on 2016/7/1
 * 邮箱: 445263848@qq.com.
 */
public interface ServerAPI {
    //网站根目录
    String baseUrl="http://172.16.102.16:8080/SSC/";
    //获取商家接口
    @GET("user/shop_home.action")
    Observable<ShopList> cityList();
    //通过商家ID获取商家信息
    @GET("/SSC/user/vendor_good.action")
    Observable<ShopInfo> shopInfo(@Query("vendorId") String vendorId);
}
