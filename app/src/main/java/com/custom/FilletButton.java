package com.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.common.Constant;
import com.common.Until;

import huisou.model.R;

/**
 * Created by admin on 2015/12/17.
 */
public class FilletButton extends Button {
    private GradientDrawable gradientDrawable = new GradientDrawable();
    private Context context;

    private int mBackgroudColor;
    private int mCornerRadius;
    private int mStrokeWidth;
    private int mStrokeColor;

    public FilletButton(Context context) {
        super(context, null);
    }

    public FilletButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context=context;
        mBackgroudColor = Color.parseColor(Constant.COLOR_BUTTON);
        mCornerRadius = Until.dip2px(context, 8);
        gradientDrawable();
        gradientDrawable();


        this.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mBackgroudColor = Color.parseColor(Constant.COLOR_BUTTON_PRESS);
                        gradientDrawable();
                        break;
                    case MotionEvent.ACTION_UP:
                        mBackgroudColor = Color.parseColor(Constant.COLOR_BUTTON);
                        gradientDrawable();
                        break;
                }
                return true;
            }
        });
    }

    public void setCornerRadius(int dp){
        mCornerRadius = Until.dip2px(context, dp);
        gradientDrawable();
    }

    public void setStrokeWidth(int dp){
        mStrokeWidth = Until.dip2px(context, dp);
        gradientDrawable();
    }

    public void setStrokeColor(int dp){
        mStrokeColor = Until.dip2px(context, dp);
        gradientDrawable();
    }

    public void gradientDrawable() {
        gradientDrawable.setColor(mBackgroudColor);
        gradientDrawable.setCornerRadius(mCornerRadius);
        gradientDrawable.setStroke(mStrokeWidth, mStrokeColor);
        setBackground(gradientDrawable);
       invalidate();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
