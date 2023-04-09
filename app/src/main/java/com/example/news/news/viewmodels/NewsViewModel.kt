package com.example.news.news.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.models.NewsModel
import com.example.news.repo.MainRepo
import com.example.news.util.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repo: MainRepo) : ViewModel() {
    private val _newsResponse: MutableSharedFlow<ApiResponse<NewsModel?>> =
        MutableSharedFlow()
    val newsResponse: SharedFlow<ApiResponse<NewsModel?>> = _newsResponse

    fun fetchNews(category: String) {
        viewModelScope.launch {
            val response = repo.fetchNews(category)
            _newsResponse.emit(response)
        }
    }
}