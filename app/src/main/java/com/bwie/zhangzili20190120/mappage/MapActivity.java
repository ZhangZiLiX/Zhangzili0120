package com.bwie.zhangzili20190120.mappage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.amap.api.maps.MapView;
import com.bwie.zhangzili20190120.R;

public class MapActivity extends AppCompatActivity {

    private MapView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        //初始化控件
        initView();
    }

    //初始化控件
    private void initView() {
        map = (MapView) findViewById(R.id.map);
    }
}
