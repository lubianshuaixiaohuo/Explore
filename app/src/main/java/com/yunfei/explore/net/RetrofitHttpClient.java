package com.yunfei.explore.net;

import android.content.Context;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author：zhouyunfei
 * date:2017/7/12 11:02
 * describe：
 */
public class RetrofitHttpClient {

    public static String baseUrl = "http://dlc2.pconline.com.cn/";
    ;

    private static final int DEFAULT_TIMEOUT = 20;
    private BaseApiService apiService;
    private static OkHttpClient okHttpClient;
    private static Context mContext;
    private static Retrofit retrofit;
    private Cache cache = null;
    private File httpCacheDirectory;


    private volatile static RetrofitHttpClient INSTANCE;



    //获取单例
    public static RetrofitHttpClient getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (RetrofitHttpClient.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RetrofitHttpClient(context);
                }
            }
        }
        return INSTANCE;
    }


    private RetrofitHttpClient(Context context) {
        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS))
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        okHttpClient.newBuilder();

        apiService = retrofit.create(BaseApiService.class);

    }

    public Subscription get(String url, Map headers, Map parameters, Subscriber<ResponseBody> subscriber) {

        return apiService.executeGet(url, headers, parameters)
                .compose(schedulersTransformer())
                .subscribe(subscriber);
    }

    public void post(String url, Map headers, Map<String, String> parameters, Subscriber<ResponseBody> subscriber) {
        apiService.executePost(url, headers, parameters)
                .compose(schedulersTransformer())
                .subscribe(subscriber);
    }

    public Subscription json(String url, Map headers, RequestBody jsonStr, Subscriber<ResponseBody> subscriber) {

        return apiService.json(url, headers, jsonStr)
                .compose(schedulersTransformer())
                .subscribe(subscriber);
    }

    public void upload(String url, Map headers, RequestBody requestBody, Subscriber<ResponseBody> subscriber) {
        apiService.upLoadFile(url, headers, requestBody)
                .compose(schedulersTransformer())
                .subscribe(subscriber);
    }

    public void download(String url, File file, final DownLoadCallBack callBack) {
        apiService.downloadFile(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DownLoadSubscriber(callBack, file));
    }

    public void download(String url, String path, String name, final DownLoadCallBack callBack) {
        apiService.downloadFile(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DownLoadSubscriber(callBack, path, name));
    }


    Observable.Transformer schedulersTransformer() {
        return new Observable.Transformer() {
            @Override
            public Object call(Object observable) {
                return ((Observable) observable).subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
