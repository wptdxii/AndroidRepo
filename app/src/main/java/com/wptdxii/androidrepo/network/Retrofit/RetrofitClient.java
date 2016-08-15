package com.wptdxii.androidrepo.network.retrofit;

import com.wptdxii.androidrepo.App;
import com.wptdxii.androidrepo.BuildConfig;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wptdxii on 2016/8/1 0001.
 */
public class RetrofitClient {
    public static final String BASE_URL = "http://gank.io/";
    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;
    public static Retrofit retrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .client(okHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();
        }
        
        return mRetrofit;
    }
    
    public static OkHttpClient okHttpClient() {
        if (mOkHttpClient == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(BuildConfig.DEBUG?
                    HttpLoggingInterceptor.Level.BODY: HttpLoggingInterceptor.Level.NONE);
            
            File httpCacheDirectory = new File(App.getInstance().getCacheDir(), "responses");
            int cacheSize = 10 * 1024 * 1024;
            Cache cache = new Cache(httpCacheDirectory, cacheSize);
            
            mOkHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .retryOnConnectionFailure(true)
                    .connectTimeout(15L, TimeUnit.SECONDS)
                    .cache(cache)
                   // .addInterceptor(new CacheInterceptor())
                    .build();
        }
        
        return mOkHttpClient;
    }
}
