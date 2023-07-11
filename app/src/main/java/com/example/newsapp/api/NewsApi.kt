package com.example.newsapp.api

import com.example.newsapp.data.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/* "https://newsdata.io/api/1/news?apikey=pub_101291dddd6941bb0da0623aee7e6345e69a7&q=anime&language=en&country=jp" */

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getBusinessNews(
        @Query("apiKey") app_id: String = "50804077c76a4c958cb85521f6d392c7",
        @Query("category") newsType: String = "business",
        @Query("country") countryCode: String = "in"
    ) : Response<NewsResponse>

    @GET("v2/top-headlines")
    suspend fun getTechnologyNews(
        @Query("apiKey") app_id: String = "50804077c76a4c958cb85521f6d392c7",
        @Query("category") newsType: String = "technology",
        @Query("country") countryCode: String = "in"
    ) : Response<NewsResponse>

    @GET("v2/top-headlines")
    suspend fun getSportsNews(
        @Query("apiKey") app_id: String = "50804077c76a4c958cb85521f6d392c7",
        @Query("category") newsType: String = "sports",
        @Query("country") countryCode: String = "in"
    ) : Response<NewsResponse>
}