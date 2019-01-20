package com.bwie.zhangzili20190120.application;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.bwie.zhangzili20190120.db.DaoMaster;
import com.bwie.zhangzili20190120.db.DaoSession;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * date:2019/1/20
 * author:张自力(DELL)
 * function:  全局配置
 */

public class MyApplication extends Application {

    private DaoMaster.DevOpenHelper mSport_db;
    private SQLiteDatabase mDb;
    private DaoMaster mDaoMaster;
    public static MyApplication instance;
    private DaoSession mDaoSession;

    public MyApplication() {
    }

    public static MyApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Fresco
        initFresco();
        //配置GreenDao
        initGreenDao();
    }

    //配置GreenDao
    private void initGreenDao() {
        //得到一个对象
        mSport_db = new DaoMaster.DevOpenHelper(this, "sport_db", null);
        mDb = mSport_db.getWritableDatabase();
        mDaoMaster = new DaoMaster(mDb);
        mDaoSession = mDaoMaster.newSession();

    }

public DaoSession getDaoSession(){
        return mDaoSession;
}

public SQLiteDatabase getDb(){
    return mDb;
}

    //Fresco
    private void initFresco() {
        Fresco.initialize(this);
    }
}
