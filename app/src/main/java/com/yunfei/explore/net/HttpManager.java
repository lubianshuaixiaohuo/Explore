package com.yunfei.explore.net;

import android.content.Context;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Subscriber;

/**
 * author：zhouyunfei
 * date：2017/7/13 19:49
 * describe：http管理类
 */

public class HttpManager {
    public static Map<String, String> headerMap = new HashMap<>();


    /*
    *
    *  public static void get(Context context, Object tag,String url, Map headers, Map parameters, Subscriber<ResponseBody> subscriber) {
        RxApiManager.get().add( tag, RetrofitHttpClient.getInstance(context).get(url, headers, parameters, subscriber));
    }
    *
    * */

    /***
     * get请求
     * @param context
     * @param url
     * @param headers
     * @param parameters
     * @param subscriber
     */
    public static void get(Context context, String url, Map headers, Map parameters, Subscriber<ResponseBody> subscriber) {
        RetrofitHttpClient.getInstance(context).get(url, headers, parameters, subscriber);
    }

    public static void get(Context context, String url, Map parameters, Subscriber<ResponseBody> subscriber) {
        RetrofitHttpClient.getInstance(context).get(url, headerMap, parameters, subscriber);
    }

    /****
     * post请求
     * @param context
     * @param url
     * @param headers
     * @param parameters
     * @param subscriber
     */
    public static void post(Context context, String url, Map headers, Map parameters, Subscriber<ResponseBody> subscriber) {
        RetrofitHttpClient.getInstance(context).post(url, headers, parameters, subscriber);
    }

    public static void post(Context context, String url, Map parameters, Subscriber<ResponseBody> subscriber) {
        RetrofitHttpClient.getInstance(context).post(url, headerMap, parameters, subscriber);
    }

    /***
     * 发送json
     * @param context
     * @param url
     * @param headers
     * @param jsonStr
     * @param subscriber
     */
    public static void json(Context context, String url, Map headers, RequestBody jsonStr, Subscriber<ResponseBody> subscriber) {
        RetrofitHttpClient.getInstance(context).json(url, headers, jsonStr, subscriber);
    }

    public static void json(Context context, String url, RequestBody jsonStr, Subscriber<ResponseBody> subscriber) {
        RetrofitHttpClient.getInstance(context).json(url, headerMap, jsonStr, subscriber);
    }


    /***
     * 下载
     * @param context
     * @param url
     * @param path
     * @param name
     * @param callBack
     */
    public static void download(Context context, String url, String path, String name, final DownLoadCallBack callBack) {
        RetrofitHttpClient.getInstance(context).download(url, path, name, callBack);
    }

    public static void download(Context context, String url, File file, final DownLoadCallBack callBack) {
        RetrofitHttpClient.getInstance(context).download(url, file, callBack);
    }

}
