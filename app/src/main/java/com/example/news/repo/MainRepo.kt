package com.example.news.repo

import com.example.news.models.NewsModel
import com.example.news.remote.ApiInterface
import com.example.news.util.ApiResponse
import javax.inject.Inject

class MainRepo @Inject constructor(private val apiInterface: ApiInterface) {
    suspend fun fetchNews(category: String):ApiResponse<NewsModel?>{
        val response = apiInterface.newsApi(category)
        return if(response.isSuccessful){
            ApiResponse.Success(response.body())
        }else{
            ApiResponse.Error(response.message())
        }
    }
}