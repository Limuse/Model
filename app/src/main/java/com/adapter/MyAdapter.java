package com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.BaseAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;
import java.util.Map;

import huisou.model.R;

/**
 * Created by huisou on 2015/12/16.
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, String>> list;

    public MyAdapter(Context context, List<Map<String, String>> list) {
        super.BaseAdapter(context,list);
        this.context = context;
        this.list = list;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        HoldVew holdVew = null;
        if (convertView == null) {
            holdVew = new HoldVew();
            convertView = LayoutInflater.from(context).inflate(R.layout.mylist_item, null);
            holdVew.img = (ImageView) convertView.findViewById(R.id.img_my);
            holdVew.textView = (TextView) convertView.findViewById(R.id.tv_my);
            convertView.setTag(holdVew);

        } else {
            holdVew = (HoldVew) convertView.getTag();
        }
        Map<String, String> map = list.get(position);
        ImageLoader.getInstance().displayImage("", holdVew.img,options);
        return convertView;
    }

    private class HoldVew {
        ImageView img;
        TextView textView;
    }
}
