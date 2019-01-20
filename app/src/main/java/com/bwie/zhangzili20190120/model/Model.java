package com.bwie.zhangzili20190120.model;

import com.bwie.zhangzili20190120.R;
import com.bwie.zhangzili20190120.item.ICallBack;
import com.bwie.zhangzili20190120.utils.RxRetrofitUtils;
import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * date:2019/1/20
 * author:张自力(DELL)
 * function:  Model  网址请求层
 *
 * 实现IModel
 */

public class Model implements IModel {

    //1 普通doGet方法（s商品请求）
    @Override
    public void doGetShopDataIM(String url, final ICallBack iCallBack, final Type type) {
        RxRetrofitUtils.getIntance().doGetShop(url, new RxRetrofitUtils.setOnClickListenerU() {
            @Override
            public void Success(String data) {
               //解析数据
                Object o = new Gson().fromJson(data, type);
                if(iCallBack!=null){
                    iCallBack.Success(o);
                }
            }

            @Override
            public void Failder(String str) {
                if(iCallBack!=null){
                    iCallBack.Failder(str);
                }
            }
        });
    }

    //商品详情
    @Override
    public void doGetXQShopDataIM(String url, int pid, final ICallBack iCallBack, final Type type) {
        RxRetrofitUtils.getIntance().doGetXQShop(url,pid,new RxRetrofitUtils.setOnClickListenerU() {
            @Override
            public void Success(String data) {
                //解析数据
                Object o = new Gson().fromJson(data, type);
                if(iCallBack!=null){
                    iCallBack.Success(o);
                }
            }

            @Override
            public void Failder(String str) {
                if(iCallBack!=null){
                    iCallBack.Failder(str);
                }
            }
        });
    }
}
