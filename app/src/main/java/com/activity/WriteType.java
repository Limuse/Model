package com.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.base.BaseActivity;
import com.common.Constant;

import huisou.model.R;

/**
 * Created by huisou on 2015/12/17.
 */
public class WriteType extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_writetype, true);
        initView();
    }
    private void initView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("需要获取");
        toolbar.setTitleTextColor(getResources().getColor(R.color.app_light));
        toolbar.setBackgroundColor(Color.parseColor(Constant.COLOR_BAR));
        toolbar.setNavigationIcon(R.mipmap.right_too);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.inflateMenu(R.menu.right_write);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
    }
    /**
     * 在绘制页面前先加载数据
     */
    private void initData(){
        String title=getIntent().getExtras().getString("title");
    }
}
