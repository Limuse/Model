package com.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.activity.AboutApp;
import com.activity.MyInfo;
import com.base.BaseFragment;
import com.custom.IlistView;

import huisou.model.R;

/**
 * Created by admin on 2015/12/14.
 */
public class FragmentMy extends BaseFragment {
    private View parentView;
    private RelativeLayout rl_img;
    private ImageView img_title, img_set;
    private TextView tv_name;
    private IlistView ilistView, ilistView2;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView = setView(R.layout.fragment_my, false);
        return parentView;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        img_title = (ImageView) parentView.findViewById(R.id.myself_image);
        img_set = (ImageView) parentView.findViewById(R.id.img_set);
        tv_name = (TextView) parentView.findViewById(R.id.myself_name);
        ilistView = (IlistView) parentView.findViewById(R.id.mylist);
        ilistView2 = (IlistView) parentView.findViewById(R.id.mylist2);
        img_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActivity(MyInfo.class);
//                Bundle bundle=new Bundle();
//                bundle.putString("title","title");
//                StartActivity(MyInfo.class, bundle);
            }
        });
        img_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActivity(AboutApp.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("title", "title");
//                StartActivity(AboutApp.class, bundle);
            }
        });


    }
}
