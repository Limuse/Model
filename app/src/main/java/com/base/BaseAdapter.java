package com.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.common.Init;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import huisou.model.R;

/**
 * Created by admin on 2015/12/15.
 */
public abstract class BaseAdapter extends android.widget.BaseAdapter {
    protected DisplayImageOptions options;
    protected Context context;

    private Toast toast = null;
    private List list;

    protected void BaseAdapter(Context context,List<?extends Map<?, ?>> list) {
        this.context = context;
        this.list = list;
        initImageLoder();
    }

    protected void BaseAdapter(Context context) {
        this.context = context;
        initImageLoder();
    }



    private void initImageLoder() {
        options = new DisplayImageOptions.Builder().cacheInMemory(true).showImageOnLoading(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .build();
    }


    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView,
                        ViewGroup parent) {
        return convertView;
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
}
