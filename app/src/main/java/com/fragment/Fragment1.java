package com.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.base.BaseFragment;
import com.common.Constant;
import com.common.Http;
import com.custom.FilletButton;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;

import huisou.model.R;

/**
 * Created by admin on 2015/12/14.
 */
public class Fragment1 extends BaseFragment {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return setView(R.layout.fragment_1,true);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final FilletButton button=(FilletButton)context.findViewById(R.id.image);
        button.setCornerRadius(20);
        button.setStrokeColor(Color.parseColor("#000000"));
        button.setStrokeWidth(2);
    }
}
