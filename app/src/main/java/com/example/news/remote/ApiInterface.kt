package com.example.news.remote

import com.example.news.models.NewsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("news")
    suspend fun newsApi(@Query("category") category: String): Response<NewsModel>
}