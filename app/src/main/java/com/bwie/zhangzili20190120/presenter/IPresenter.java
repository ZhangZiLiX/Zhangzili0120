package com.bwie.zhangzili20190120.presenter;

/**
 * date:2019/1/20
 * author:张自力(DELL)
 * function:  P层的接口
 *
 * 用于方法的抽取
 */

public interface IPresenter {

    //普通的doGet方法
    void doGetShopDataIP();
    //详情
    void doGetXQShopDataIP(int pid);

}
