package com.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.BaseActivity;
import com.common.Constant;

import huisou.model.R;

/**
 * Created by huisou on 2015/12/16.
 */
public class AboutApp extends BaseActivity implements View.OnClickListener {
    private ImageView img_ab;
    private Button btn_ab;
    private RelativeLayout rl_ab, rl_advice;
    private TextView phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_myapp, true);
        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("关于APP");
        toolbar.setTitleTextColor(getResources().getColor(R.color.app_light));
        toolbar.setBackgroundColor(Color.parseColor(Constant.COLOR_BAR));
        toolbar.setNavigationIcon(R.mipmap.right_too);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        img_ab = (ImageView) findViewById(R.id.ab_img);
        btn_ab = (Button) findViewById(R.id.submit_ab);
        rl_ab = (RelativeLayout) findViewById(R.id.rl_ab);
        rl_advice = (RelativeLayout) findViewById(R.id.rl_abadvice);
        phone = (TextView) findViewById(R.id.tv_phone);

        btn_ab.setOnClickListener(this);
        rl_ab.setOnClickListener(this);
        rl_advice.setOnClickListener(this);
        phone.setOnClickListener(this);

    }

    /**
     * 在绘制页面前先加载数据
     */
    private void initData() {
        String title = getIntent().getExtras().getString("title");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.rl_ab) {
            StartActivity(UsualyType.class);
//            Bundle bundle = new Bundle();
//            bundle.putString("title", "title");
//            StartActivity(AboutApp.class, bundle);
        }
        if (id == R.id.rl_abadvice) {
            StartActivity(Advices.class);
//            Bundle bundle = new Bundle();
//            bundle.putString("title", "title");
//            StartActivity(AboutApp.class, bundle);
        }
        if (id == R.id.tv_phone) {
        }

        if (id == R.id.submit_ab) {
            //退出登录
        }

    }
}
