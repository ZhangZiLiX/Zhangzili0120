package com.bwie.zhangzili20190120.utils;

import android.util.Log;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * date:2019/1/20
 * author:张自力(DELL)
 * function:  网络请求工具类
 */

public class RxRetrofitUtils {

    //单例
    private static volatile RxRetrofitUtils intance;
    private final Retrofit.Builder mRetrofitClient;

    //构造方法
    private RxRetrofitUtils() {
        //创建日志拦截器
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("http日志拦截器信息打印:", "log: " + message);
            }
        });
        //添加日志级别
        httpLoggingInterceptor.setLevel(level);
        //创建Client对象
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = builder.connectTimeout(5000, TimeUnit.SECONDS)
                .readTimeout(5000, TimeUnit.SECONDS)
                .writeTimeout(5000, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();

        //创建Retrofit对象
        mRetrofitClient = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient);

    }
    //使用双重锁机制
    public static RxRetrofitUtils getIntance(){
        if(intance==null){
            synchronized (RxRetrofitUtils.class){
                if(intance==null){
                    intance = new RxRetrofitUtils();
                }
            }
        }
        return intance;
    }

    private RetrofitIView getRetrofitIView(String url) {
        Retrofit retrofit = mRetrofitClient.baseUrl(url)
                .build();
        RetrofitIView retrofitIView = retrofit.create(RetrofitIView.class);
        return retrofitIView;
    }

    //创建一个方法  所有商品
    public RxRetrofitUtils doGetShop(String url, setOnClickListenerU listenerU){
        //调用方法得到RetrofitIView对象
        RetrofitIView retrofitIView = getRetrofitIView(url);
        retrofitIView.doGetShopData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserveble(listenerU));
        return intance;
    }

    //创建一个方法  详情
    public RxRetrofitUtils doGetXQShop(String url, int pid, setOnClickListenerU listenerU){
        //调用方法得到RetrofitIView对象
        RetrofitIView retrofitIView = getRetrofitIView(url);
        retrofitIView.doGetXQShopData(pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserveble(listenerU));
        return intance;
    }

    //创建一个方法
    private Observer getObserveble(final setOnClickListenerU listenerU){

        Observer observer = new Observer<ResponseBody>(){
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
               if(listenerU!=null){
                   listenerU.Failder(e.getMessage());
               }
            }

            @Override
            public void onNext(ResponseBody responseBody) {
               if(listenerU!=null){
                   try {
                       String result = responseBody.string();
                       listenerU.Success(result);
                   } catch (IOException e) {
                       e.printStackTrace();
                       if(listenerU!=null){
                           listenerU.Failder(e.getMessage());
                       }
                   }
               }
            }
        };
        return observer;
    }



    //定义一个接口
    public interface setOnClickListenerU{
        //成功的方法
        void Success(String data);
        //失败方法
        void Failder(String str);
    }

}
