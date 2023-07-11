package com.example.newsapp.repository

import com.example.newsapp.api.RetrofitInstance

class NewsRepository {

    suspend fun getBusinessNews() = RetrofitInstance.api.getBusinessNews()
    suspend fun getSportsNews() = RetrofitInstance.api.getSportsNews()
    suspend fun getTechnology() = RetrofitInstance.api.getTechnologyNews()
}