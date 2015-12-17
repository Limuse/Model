package com.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by huisou on 2015/8/5.
 * 自定义listview-----仅用于购物车中listview 嵌套listview
 * 让嵌套的listviewitem的高度自适应，重写onMeasure
 */
public class IlistView extends ListView {
    public IlistView(Context context) {
        super(context);
    }

    public IlistView(Context context, AttributeSet attrs) {

        super(context, attrs);

    }

    public IlistView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);

    }

    @Override

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,

                MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
