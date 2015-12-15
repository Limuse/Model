package com.common;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
/**
 * Created by admin on 2015/12/10.
 */
public class Http {

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, TextHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, TextHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return Constant.BASE_URL + relativeUrl;
    }
}
