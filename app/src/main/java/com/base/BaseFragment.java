package com.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.common.Constant;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import huisou.model.R;

/**
 * Created by admin on 2015/12/14.
 */
public abstract class BaseFragment extends Fragment {
    protected Toolbar toolbar;
    protected DisplayImageOptions options;
    protected BaseActivity context;

    private ViewGroup viewGroup;
    private Toast toast = null;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = (BaseActivity) activity;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initImageLoder();
    }

    public View setView(int layoutId, Boolean isShowBar) {
        if (isShowBar) {
            viewGroup = (ViewGroup) context.getLayoutInflater().inflate(R.layout.activity_base, null);
            View v = context.getLayoutInflater().inflate(layoutId, viewGroup, false);
            viewGroup.addView(v);
            initBar();
        } else {
            viewGroup = (ViewGroup) context.getLayoutInflater().inflate(layoutId, null);
        }
        return viewGroup;
    }

    private void initImageLoder() {
        options = new DisplayImageOptions.Builder().cacheInMemory(true).showImageOnLoading(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .build();
    }

    private void initBar() {
        toolbar = (Toolbar) viewGroup.findViewById(R.id.toolbar);
        toolbar.setTitle("标题");
        toolbar.setTitleTextColor(getResources().getColor(R.color.app_light));
        toolbar.setBackgroundColor(Color.parseColor(Constant.COLOR_BAR));
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    protected void Log(String string) {
        Log.e("LOG", string);
    }

    protected void Toast(String string) {
        if (toast == null) {
            toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
        } else {
            toast.setText(string);
        }
        toast.show();
    }

    protected void StartActivity(Class<?> cls) {
        Intent intent = new Intent(context, cls);
        startActivity(intent);
    }

    protected void StartActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(context, cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
