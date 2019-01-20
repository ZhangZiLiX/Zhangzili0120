package com.bwie.zhangzili20190120.model;

import com.bwie.zhangzili20190120.item.ICallBack;

import java.lang.reflect.Type;

/**
 * date:2019/1/20
 * author:张自力(DELL)
 * function:  Model层的抽象方法
 */

public interface IModel {

    //1 普通的doGet方法(商品)
    void doGetShopDataIM(String url, ICallBack iCallBack, Type type);

    //2 详情商品
    void doGetXQShopDataIM(String url, int pid, ICallBack iCallBack, Type type);


}
