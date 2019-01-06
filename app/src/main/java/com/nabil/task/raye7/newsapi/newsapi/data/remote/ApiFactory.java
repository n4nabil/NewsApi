package com.nabil.task.raye7.newsapi.newsapi.data.remote;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Ahmad Shubita on 8/1/17.
 */

public class ApiFactory {


    //New api version
    public static final String BASE_URL = "https://newsapi.org/v2/";

    //API Key
    public static final String API_KEY = "533742c6bbf94ea1ab283b9ab4d878b3";


    public static UsersService create() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(UsersService.class);
    }

}
