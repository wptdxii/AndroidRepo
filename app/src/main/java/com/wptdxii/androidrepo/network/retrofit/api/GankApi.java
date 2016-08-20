package com.wptdxii.androidrepo.network.retrofit.api;

import com.wptdxii.androidrepo.model.BaseModel;
import com.wptdxii.androidrepo.model.Benefit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by wptdxii on 2016/8/1 0001.
 */
public interface GankApi {
    
    @GET("api/data/福利/{number}/{page}")
    Call<BaseModel<ArrayList<Benefit>>> defaultBenefits(
            @Path("number") int number,
            @Path("page") int page
    );
    @GET("api/data/福利/{number}/{page}")
    Observable<BaseModel<ArrayList<Benefit>>> rxBevefits(
            @Path("number") int number,
            @Path("page") int page
    );
}