package com.bwie.zhangzili20190120.utils;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * date:2019/1/20
 * author:张自力(DELL)
 * function:
 */

public interface RetrofitIView {

    //普通get方法
    //商品类
    @GET("product/searchProducts?keywords=笔记本&page=1")
    Observable<ResponseBody> doGetShopData();

    //详情
    //http://www.zhaoapi.cn/product/getProductDetail?pid=1
    @GET("product/getProductDetail")
    Observable<ResponseBody> doGetXQShopData(@Query("pid") int pid);



}
