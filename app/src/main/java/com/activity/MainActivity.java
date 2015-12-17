package com.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.adapter.MainTabAdapter;
import com.alibaba.fastjson.JSON;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.base.BaseActivity;
import com.common.Constant;
import com.common.Http;
import com.common.Init;
import com.custom.NoScrollGridView;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import huisou.model.R;

/**
 * Created by admin on 2015/12/8.
 */
public class MainActivity extends BaseActivity {
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private Fragment[] fragments;
    private NoScrollGridView gridView;
    private String hideTag;
    private MainTabAdapter adapter;


    private LocationClient mLocClient;
    private LocationClientOption option = new LocationClientOption();
    private LocationListenner locationListenner = new LocationListenner();

    private  List<Map<String, String>> list = new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_main, false);
        initData();
        initView();
        initClick();
        location();
//            Log("sas");
//        Toast("sas");
//        StartActivity(MainActivity.class);
//        RequestParams requestParams=new RequestParams();
//        requestParams.put("aa","aaa");
//        Http.post("sss", requestParams, new TextHttpResponseHandler() {
//            public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
//
//            }
//            public void onSuccess(int i, Header[] headers, String s) {
//                entity= JSON.parseObject(s,entit.class);
//
//            }
//        });

    }

    private void location() {
        //ImageLoader.getInstance().displayImage(context,"",View);
        mLocClient = new LocationClient(getApplicationContext());
        mLocClient.registerLocationListener(locationListenner);
        option.setOpenGps(true);
        option.setAddrType("all");
        option.setCoorType("bd09ll");
        option.setScanSpan(5000);
        mLocClient.setLocOption(option);
        mLocClient.start();
    }

    private void initClick() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (hideTag.equals(position + ""))
                    return;
                adapter.setChecdIndex(position);
                switchFragment(fragments[position], position + "");
            }
        });
    }


    private void initData() {
        list.add(new HashMap<String, String>());
        list.add(new HashMap<String, String>());
        list.add(new HashMap<String, String>());
        Constant.TAB_Number=list.size();
    }

    private void initView() {
        gridView = (NoScrollGridView) findViewById(R.id.gridView);
        gridView.setBackgroundColor(Color.parseColor(Constant.COLOR_TAB));
        gridView.setNumColumns(Constant.TAB_Number);
        adapter = new MainTabAdapter(context, list);
        gridView.setAdapter(adapter);

        fragments = Init.setHomeFragment(Constant.TAB_Number);
        switchFragment(fragments[0], 0 + "");
    }

    public void switchFragment(Fragment fragment, String tag) {
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        Fragment tagFragment = mFragmentManager.findFragmentByTag(tag);
        if (tagFragment == null) {
            mFragmentTransaction.add(R.id.tabcontent, fragment, tag);
        } else {
            mFragmentTransaction.show(tagFragment);
        }
        tagFragment = mFragmentManager.findFragmentByTag(hideTag);
        if (tagFragment != null) {
            mFragmentTransaction.hide(tagFragment);
        }
        hideTag = tag;
        mFragmentTransaction.commit();
    }

    public class LocationListenner implements BDLocationListener {
        public void onReceiveLocation(BDLocation location) {
            Toast(location.getAddrStr());
            mLocClient.stop();
        }
    }
}
