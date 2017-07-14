package com.yunfei.explore.net;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;

/**
 * author：zhouyunfei
 * date:2017/7/12 15:20
 * describe：下载辅助类
 */
public class DownLoadHelper {

    private DownLoadCallBack callBack;
    private static final String TAG = "DownLoadHelper";
    private static String APK_CONTENTTYPE = "application/vnd.android.package-archive";
    private static String PNG_CONTENTTYPE = "image/png";
    private static String JPG_CONTENTTYPE = "image/jpg";
    private static String fileSuffix = ".bat";
    private Handler handler;

    public DownLoadHelper(DownLoadCallBack callBack) {
        this.callBack = callBack;
    }


    public boolean writeResponseBodyToDisk(String path, String name, ResponseBody body) {
        String type = body.contentType().toString();

        if (type.equals(APK_CONTENTTYPE)) {
            fileSuffix = ".apk";
        } else if (type.equals(PNG_CONTENTTYPE)) {
            fileSuffix = ".png";
        } else if (type.equals(JPG_CONTENTTYPE)) {
            fileSuffix = ".jpg";
        }
        final String fileName = TextUtils.isEmpty(name) ? System.currentTimeMillis() + fileSuffix : name;
        final String abslouteName = path + File.separator + fileName;
        File file = new File(abslouteName);
        return writeResponseBodyToDisk(file, body);
    }


    public boolean writeResponseBodyToDisk(final File file, ResponseBody body) {

        try {
            if (file.exists()) {
                file.delete();
            }

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];
                final long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;
                inputStream = body.byteStream();
                outputStream = new FileOutputStream(file);

                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                    if (callBack != null) {
                        handler = new Handler(Looper.getMainLooper());
                        final long finalFileSizeDownloaded = fileSizeDownloaded;
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callBack.onProgress(finalFileSizeDownloaded);
                            }
                        });

                    }
                }

                outputStream.flush();
                if (callBack != null) {
                    handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onSucess(file, fileSize);

                        }
                    });
                }

                return true;
            } catch (IOException e) {
                if (callBack != null) {
                    callBack.onError(e);
                }
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            if (callBack != null) {
                callBack.onError(e);
            }
            return false;
        }
    }
}
