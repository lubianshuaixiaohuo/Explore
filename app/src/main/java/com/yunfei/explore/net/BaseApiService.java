package com.yunfei.explore.net;


import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * author：zhouyunfei
 * date:2017/7/12 10:57
 * describe：
 */
public interface BaseApiService {

    @GET()
    Observable<ResponseBody> executeGet(
            @Url() String url,
            @HeaderMap Map<String, String> headerMap,
            @QueryMap Map<String, String> map);

    @POST()
    Observable<ResponseBody> executePost(
            @Url String url,
            @HeaderMap Map<String, String> headerMap,
            @QueryMap Map<String, String> map);

    @POST()
    Observable<ResponseBody> json(
            @Url String url,
            @HeaderMap Map<String, String> headerMap,
            @Body RequestBody jsonStr);

    @Multipart
    @POST()
    Observable<ResponseBody> upLoadFile(
            @Url String url,
            @HeaderMap Map<String, String> headerMap,
            @Part("image\"; filename=\"image.jpg") RequestBody requestBody);

    @POST()
    Call<ResponseBody> uploadFiles(
            @Url String url,
            @HeaderMap Map<String, String> headerMap,
            @Part("filename") String description,
            @PartMap() Map<String, RequestBody> map);


    @GET()
    Observable<ResponseBody> downloadFile(@Url String fileUrl);

}
