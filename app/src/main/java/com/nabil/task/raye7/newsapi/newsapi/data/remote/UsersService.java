package com.nabil.task.raye7.newsapi.newsapi.data.remote;


import com.nabil.task.raye7.newsapi.newsapi.data.models.NewsResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Ahmad Shubita on 8/1/17.
 */

//https://newsapi.org/v2/everything?sources=usa-today&language=en&pagesize=1&page=1&apiKey=533742c6bbf94ea1ab283b9ab4d878b3
public interface UsersService {

    @GET ("/v2/everything?sources=usa-today&language=en&pagesize=20&page=1&apiKey=533742c6bbf94ea1ab283b9ab4d878b3")
    Observable<NewsResponse> fetchUsers(

    );


    @GET ("/v2/everything?sources=usa-today&language=en&pagesize=20&apiKey=533742c6bbf94ea1ab283b9ab4d878b3")
    Observable<NewsResponse> fetchUsers(
            @Query("page") String page
    );

    @GET ("/v2/everything")
    Observable<NewsResponse> fetchUsers(
            @Query("sources") String sources,
            @Query("language") String language,
            @Query("pagesize") String pagesize,
            @Query("page") String page,
            @Query("apiKey") String apiKey

    );


}
