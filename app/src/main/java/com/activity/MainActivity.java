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
import com.base.BaseActivity;
import com.common.Constant;
import com.common.Init;
import com.custom.NoScrollGridView;

import java.util.ArrayList;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_main,false);
        initView();
        initData();
        initClick();
    }

    private void initClick() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (hideTag.equals(position+""))
                    return;
                adapter.setChecdIndex(position);
                switchFragment(fragments[position], position + "");
        }
        });
    }

    private void initData() {
        fragments= Init.setHomeFragment(Constant.TAB_Number);
        switchFragment(fragments[0], 0+"");
    }

    private void initView() {
        gridView=(NoScrollGridView)findViewById(R.id.gridView);
        gridView.setNumColumns(Constant.TAB_Number);
        List<Map<String,String>>list=new ArrayList<>();
         adapter=new MainTabAdapter(context,list);
        gridView.setAdapter(adapter);
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
}
