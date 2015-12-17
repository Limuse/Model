package com.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.base.BaseActivity;
import com.common.Constant;

import huisou.model.R;

/**
 * Created by huisou on 2015/12/17.
 */
public class Advices extends BaseActivity {
    private EditText et1,et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.acitivity_advices, true);
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
        toolbar.inflateMenu(R.menu.right_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });

        et1=(EditText)findViewById(R.id.et_backa);
        et2=(EditText)findViewById(R.id.ed_fid);
    }

    /**
     * 在绘制页面前先加载数据
     */
    private void initData(){
        String title=getIntent().getExtras().getString("title");
    }
}
