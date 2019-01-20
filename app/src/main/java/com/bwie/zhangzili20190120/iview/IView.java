package com.bwie.zhangzili20190120.iview;

/**
 * date:2019/1/20
 * author:张自力(DELL)
 * function:  接口
 */

public interface IView<T> {
    //成功
    void onSuccessI(T t);
    //失败方法
    void onFailderI(String str);
}
