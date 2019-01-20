package com.bwie.zhangzili20190120.shoppage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.zhangzili20190120.R;
import com.bwie.zhangzili20190120.application.MyApplication;
import com.bwie.zhangzili20190120.db.ShopBeanDaoDao;
import com.bwie.zhangzili20190120.greendao.ShopBeanDao;
import com.bwie.zhangzili20190120.iview.IView;
import com.bwie.zhangzili20190120.mappage.MapActivity;
import com.bwie.zhangzili20190120.presenter.Presenter;
import com.bwie.zhangzili20190120.xqshoppage.XQShopActivity;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity implements IView, View.OnClickListener {

    private Button btnLoding;
    private TextView txtZong;
    private TextView txtXiao;
    private TextView txtJia;
    private TextView txtShai;
    private RecyclerView rlvShop;
    private List<ShopBean.DataBean> mListshop;
    private ShopAdapter mShopAdapter;
    private Presenter mPresenter;
    private ShopBeanDaoDao mShopBeanDaoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        //初始化Dao层
        initDao();
        //初始化控件
        initView();
        //初始化list 和 adapter
        initListAndAdapter();
        //初始化Presenter
        initPresenter();
        //Adapter点击事件
        setAdapterOnClick();

    }

    //初始化Dao层
    private void initDao() {
        //初始化Dao层
        //mShopBeanDaoDao = new MyApplication().getDaoSession().getShopBeanDaoDao();
    }

    //Adapter点击事件
    private void setAdapterOnClick() {
        //点击跳转事件
        mShopAdapter.setSetShopOnClickListener(new ShopAdapter.setShopOnClickListener() {
            @Override
            public void setPid(int pid) {
                Toast.makeText(StartActivity.this,"选择了:"+pid+"号商品",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(StartActivity.this, XQShopActivity.class);
                intent.putExtra("pid",pid);
                startActivity(intent);

            }
        });




    }

    //初始化Presenter
    private void initPresenter() {
        mPresenter = new Presenter();
        mPresenter.attach(this);
        mPresenter.doGetShopDataIP();
    }

    //初始化list 和 adapter
    private void initListAndAdapter() {
        //商品的list
        mListshop = new ArrayList<>();

        //adapter
        mShopAdapter = new ShopAdapter(this, mListshop);

        //添加adapter
        rlvShop.setAdapter(mShopAdapter);

    }

    //初始化控件
    private void initView() {
        btnLoding = (Button) findViewById(R.id.btn_loding);
        txtZong = (TextView) findViewById(R.id.txt_zong);
        txtXiao = (TextView) findViewById(R.id.txt_xiao);
        txtJia = (TextView) findViewById(R.id.txt_jia);
        txtShai = (TextView) findViewById(R.id.txt_shai);
        rlvShop = (RecyclerView) findViewById(R.id.rlv_shop);

        //商品布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rlvShop.setLayoutManager(layoutManager);

        //综合等按钮设置
        setBtnColorChange();


        //事件监听
        btnLoding.setOnClickListener(this);
        txtZong.setOnClickListener(this);
        txtXiao.setOnClickListener(this);
        txtJia.setOnClickListener(this);
        txtShai.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_loding://点击定位
                 //点击定位进行跳转 地图
                Intent intent = new Intent(StartActivity.this, MapActivity.class);
                startActivity(intent);
                break;

            case R.id.txt_zong://点击综合
                txtZong.setTextColor(Color.RED);
                txtXiao.setTextColor(Color.BLACK);
                txtJia.setTextColor(Color.BLACK);
                txtShai.setTextColor(Color.BLACK);
                break;

            case R.id.txt_xiao://点击销量
                txtZong.setTextColor(Color.BLACK);
                txtXiao.setTextColor(Color.RED);
                txtJia.setTextColor(Color.BLACK);
                txtShai.setTextColor(Color.BLACK);
                break;

            case R.id.txt_jia://点击价格
                txtZong.setTextColor(Color.BLACK);
                txtXiao.setTextColor(Color.BLACK);
                txtJia.setTextColor(Color.RED);
                txtShai.setTextColor(Color.BLACK);
                break;

            case R.id.txt_shai://点击销量
                txtZong.setTextColor(Color.BLACK);
                txtXiao.setTextColor(Color.BLACK);
                txtJia.setTextColor(Color.BLACK);
                txtShai.setTextColor(Color.RED);
                break;
        }
    }

    //综合等按钮设置 (默认设置)
    private void setBtnColorChange() {
        txtZong.setTextColor(Color.RED);
        txtXiao.setTextColor(Color.BLACK);
        txtJia.setTextColor(Color.BLACK);
        txtShai.setTextColor(Color.BLACK);
    }


    @Override
    public void onSuccessI(Object o) {
        if(o instanceof ShopBean){
            ShopBean shopBean = (ShopBean) o;
            if(!shopBean.equals("")){
                List<ShopBean.DataBean> data = shopBean.getData();
                mListshop.clear();
                mListshop.addAll(data);
                mShopAdapter.notifyDataSetChanged();

                ShopBeanDao shopBeanDao = new ShopBeanDao();
                shopBean.setData(mListshop);
            }

        }
    }

    @Override
    public void onFailderI(String str) {
        Toast.makeText(this,""+str,Toast.LENGTH_SHORT).show();
    }

    //销毁
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //防止内存泄漏
        if(mPresenter!=null){
            mPresenter.datach();
        }
    }
}
