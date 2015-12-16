package com.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.base.BaseAdapter;
import com.common.Constant;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import huisou.model.R;

/**
 * Created by admin on 2015/12/15.
 */
public class MainTabAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, String>> list;
    private List<Boolean> isChecd = new ArrayList<>();


    public MainTabAdapter(Context context, List<Map<String, String>> list) {
        super.BaseAdapter(context,list);
        this.context = context;
        this.list = list;
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                isChecd.add(i, true);
            } else {
                isChecd.add(i, false);
            }
        }
    }

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
        if(isChecd.get(position)){
            viewHolder.imageView.setImageResource(R.mipmap.mainp);
        }else {
            viewHolder.imageView.setImageResource(R.mipmap.mainn);
        }

        return convertView;
    }

    private class ViewHolder {
        ImageView imageView;
    }

    public void setChecdIndex(int index){
        for (int i = 0; i <  list.size(); i++) {
            if (i == index) {
                isChecd.add(i, true);
            } else {
                isChecd.add(i, false);
            }
        }
        notifyDataSetChanged();
    }
}
