package com;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


/**
 * Created by admin on 2015/6/29.
 */
public class MyApplication extends Application {
    private static final String TAG = "JPush";

    private static MyApplication instance;

    private static boolean isLog = true;

    public static boolean isLog() {
        return isLog;
    }

    public static void setIsLog(boolean isLog) {
        MyApplication.isLog = isLog;
    }

    public void onCreate() {
        super.onCreate();
        instance = this;
        initImageLoader();
    }

    private void initImageLoader() {
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(this);
        ImageLoader.getInstance().init(configuration);
    }

    //    public static boolean isLogin(Context context) {
//        if (LSharePreference.getInstance(context).getBoolean("login")) {
//            return true;
//        } else {
//            T.ss("请登录后操作");
//            Intent intent = new Intent(context, LoginMain.class);
//            context.startActivity(intent);
//            return LSharePreference.getInstance(context).getBoolean("login");
//        }
//    }
    public static MyApplication getInstance() {
        return instance;
    }

}
