package com.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.Constant;

import java.util.List;
import java.util.Map;

import huisou.model.R;

/**
 * Created by admin on 2015/12/15.
 */
public class MainTabAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, String>> list;


    public MainTabAdapter(Context context, List<Map<String, String>> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.activity_main_tab_item, null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(position==0){
            convertView.setBackgroundColor(Color.parseColor(Constant.COLOR_TAB_PRESS));
        }else {
            convertView.setBackgroundColor(Color.parseColor(Constant.COLOR_TAB_DEFAULT));
        }
        viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
        return convertView;
    }


    private class ViewHolder {
        ImageView imageView;
    }
}
