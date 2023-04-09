package com.example.news.news.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.adapter.NewsAdapter
import com.example.news.databinding.ActivityMainBinding
import com.example.news.news.viewmodels.NewsViewModel
import com.example.news.util.ApiResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        observe()
        newsApi()
    }
    private fun newsApi(){
        viewModel.fetchNews(WORLD)
    }
    private fun observe(){
        lifecycleScope.launch {
            viewModel.newsResponse.collect {
                when (it) {
                    is ApiResponse.Loading -> {

                    }
                    is ApiResponse.Success ->{
                        binding?.recyclerView?.layoutManager = LinearLayoutManager(this@MainActivity)
                        val adapter = it.data?.data?.let { it1 -> NewsAdapter(it1) }
                        binding?.recyclerView?.adapter = adapter
                    }
                    is ApiResponse.Error -> {
                        Toast.makeText(
                            this@MainActivity,
                            it.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    companion object{
        const val WORLD = "world"
    }
}
