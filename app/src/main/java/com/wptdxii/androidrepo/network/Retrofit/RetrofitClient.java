package com.wptdxii.androidrepo.network.retrofit;

import com.wptdxii.androidrepo.App;
import com.wptdxii.androidrepo.BuildConfig;
import com.wptdxii.androidrepo.network.okhttp.OkClient;
import com.wptdxii.androidrepo.network.retrofit.converter.FastJsonConverterFactory;

import java.io.File;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by wptdxii on 2016/8/1 0001.
 * 单例模式
 */
public class RetrofitClient {
    private static final long TIMEOUT_READ = 15L;
    private static final long TIMEOUT_CONNECTION = 15L;
    
    public static final String BASE_URL = "http://gank.io/";
    
    
    private static final String API_PRODUCT_URL = BASE_URL;
    private static final String API_DEV_URl = BASE_URL;
    
    private static final boolean IS_DEV = BuildConfig.DEBUG;
    private static RetrofitClient mInstance;
    private static RetrofitClient mSyncInstance;
    
    private Retrofit mRetrofit;
    
    private static class RetrofitClientHolder {
        private static RetrofitClient mInstance = new RetrofitClient();
    }
    
    private static class SyncRetrofitClientHolder {
        private static RetrofitClient mSyncInstance = new RetrofitClient(false);
    }
    
    public <T> T createApi(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }
    
    public static RetrofitClient getInstance() {
        return RetrofitClientHolder.mInstance;
    }
    
    public static RetrofitClient getSynchronousInstance() {
        return SyncRetrofitClientHolder.mSyncInstance;
    }
    
    //私有化构造器
    private RetrofitClient() {
        this(true);
    }
    
    private RetrofitClient(boolean useRxJava) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(FastJsonConverterFactory.create())
//                .addConverterFactory(JacksonConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.DEBUG ? API_DEV_URl : API_PRODUCT_URL)
                .client(OkClient.getInstance().getClient());
//                .client(getClient());

        if (useRxJava) {
            builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        }
        mRetrofit = builder.build();
    }

    
    private OkHttpClient getClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG?
                HttpLoggingInterceptor.Level.BODY: HttpLoggingInterceptor.Level.NONE);

        //设置缓存目录
        File httpCacheDirectory = new File(App.getInstance().getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        
        SSLSocketFactory sslSocketFactory = null;
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType)
                                throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType)
                                throws CertificateException {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return new OkHttpClient.Builder()
                //.addInterceptor(new HeaderInterceptor())
                .addInterceptor(loggingInterceptor)
               // .addInterceptor(new OfflineCacheInterceptor())//设置缓存策略
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                //.cache(cache)//设置缓存目录
               //.sslSocketFactory(sslSocketFactory)
                .build();
    }
    
    
}
