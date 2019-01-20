package com.bwie.zhangzili20190120.presenter;

import com.bwie.zhangzili20190120.item.ICallBack;
import com.bwie.zhangzili20190120.iview.IView;
import com.bwie.zhangzili20190120.model.Model;
import com.bwie.zhangzili20190120.shoppage.ShopBean;
import com.bwie.zhangzili20190120.utils.API;
import com.bwie.zhangzili20190120.xqshoppage.bean.XQShopBean;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * date:2019/1/20
 * author:张自力(DELL)
 * function:  p层实现
 *
 * 实现IPresenter接口  及其方法
 */

public class Presenter implements IPresenter {

    //初始化IView  和 Model对象
    private IView mIView;
    private Model mModel;
    //实现关联
    public void attach(IView iView){
        mIView = iView;
        mModel = new Model();
    }


    //普通的doget方法
    @Override
    public void doGetShopDataIP() {

        Type type = new TypeToken<ShopBean>(){}.getType();

        //调用方法
        mModel.doGetShopDataIM(API.APIShopURL, new ICallBack() {
            @Override
            public void Success(Object o) {
                //得到数据
                if(mIView!=null){
                    mIView.onSuccessI(o);
                }
            }

            @Override
            public void Failder(String str) {
                if(mIView!=null){
                    mIView.onFailderI(str);
                }
            }
        },type);


    }

    //商品详情
    @Override
    public void doGetXQShopDataIP(int pid) {
        Type type = new TypeToken<XQShopBean>(){}.getType();

        //调用方法
        mModel.doGetXQShopDataIM(API.APIShopURL, pid, new ICallBack() {
            @Override
            public void Success(Object o) {
                //得到数据
                if(mIView!=null){
                    mIView.onSuccessI(o);
                }
            }

            @Override
            public void Failder(String str) {
                if(mIView!=null){
                    mIView.onFailderI(str);
                }
            }
        },type);

    }

    //解除关联
    public void datach(){
        if(mIView!=null){
            mIView=null;
        }

        if(mModel!=null){
            mModel=null;
        }
    }

}
