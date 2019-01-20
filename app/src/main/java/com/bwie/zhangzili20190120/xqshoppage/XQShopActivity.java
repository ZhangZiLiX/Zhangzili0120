package com.bwie.zhangzili20190120.xqshoppage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwie.zhangzili20190120.R;
import com.bwie.zhangzili20190120.application.MyApplication;
import com.bwie.zhangzili20190120.db.ShopBeanDaoDao;
import com.bwie.zhangzili20190120.iview.IView;
import com.bwie.zhangzili20190120.presenter.Presenter;
import com.bwie.zhangzili20190120.xqshoppage.bean.XQShopBean;
import com.stx.xhb.xbanner.XBanner;

public class XQShopActivity extends AppCompatActivity implements IView, View.OnClickListener {

    private ImageView imgTouxiang;
    private TextView txtShard;
    private XBanner xbanner;
    private TextView txtXqshopTitle;
    private TextView txtXqshopContent;
    private TextView txtXqshopPrice;
    private Button btnGobycar;
    private int mPid;
    private ImageView mImgVP;
    private ShopBeanDaoDao mShopBeanDaoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xqshop);
        //初始化Dao层
        initDao();
        //初始化控件
        initView();
        //接收穿过来的值
        getIntentPid();
        //初始化Presenter
        initPresenter();



    }

    //初始化Dao层
    private void initDao() {
        //mShopBeanDaoDao = new MyApplication().getDaoSession().getShopBeanDaoDao();
    }

    //初始化Presenter
    private void initPresenter() {
        Presenter presenter = new Presenter();
        presenter.attach(this);
        presenter.doGetXQShopDataIP(mPid);
    }

    //接收穿过来的值
    private void getIntentPid() {
        Intent intent = getIntent();
        mPid = intent.getIntExtra("pid", 0);
        Toast.makeText(this, "选择了商品" + mPid, Toast.LENGTH_SHORT).show();
    }

    //初始化控件
    private void initView() {
        imgTouxiang = (ImageView) findViewById(R.id.img_touxiang);
        txtShard = (TextView) findViewById(R.id.txt_shard);
        xbanner = (XBanner) findViewById(R.id.xbanner);
        mImgVP = findViewById(R.id.img_vp);
        txtXqshopTitle = (TextView) findViewById(R.id.txt_xqshop_title);
        txtXqshopContent = (TextView) findViewById(R.id.txt_xqshop_content);
        txtXqshopPrice = (TextView) findViewById(R.id.txt_xqshop_price);
        btnGobycar = (Button) findViewById(R.id.btn_gobycar);


        //点击事件
        btnGobycar.setOnClickListener(this);
    }

    //点击
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_gobycar://加入购物车
                Toast.makeText(this,"加入成功",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //成功得到数据
    @Override
    public void onSuccessI(Object o) {
        if(o instanceof XQShopBean){
            final XQShopBean xqShopBean = (XQShopBean) o;


            xbanner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    //xbanner
                }
            });

            XQShopBean.DataBean data = xqShopBean.getData();
            if(!data.equals("")){
                String images = data.getImages();
                String[] split = images.split("\\|");
                String replace = split[0].replace("https", "http");
                Glide.with(this).load(replace).into(mImgVP);
                txtXqshopTitle.setText(data.getTitle());
                txtXqshopContent.setText(data.getSubhead());
                txtXqshopPrice.setText(data.getPrice()+"");
            }

        }
    }

    @Override
    public void onFailderI(String str) {
        Toast.makeText(XQShopActivity.this,""+str,Toast.LENGTH_SHORT).show();
    }

    //注销
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
