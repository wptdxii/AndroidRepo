package com.wptdxii.androidrepo.network.retrofit.api;

import com.wptdxii.androidrepo.network.retrofit.RetrofitClient;

/**
 * Created by wptdxii on 2016/8/19 0019.
 * 封装类接口，接口实例直接通过此处获得
 */
public class ApiFactory {
    private static GankApi mGankApi;
    
    //GankApi
    public static GankApi getGankApi() {
        if (mGankApi == null) {
            mGankApi = RetrofitClient.getInstance().createApi(GankApi.class);
        }
        
        return mGankApi;
    }
}
