package com.example.newsapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.Resource
import com.example.newsapp.data.NewsResponse
import com.example.newsapp.repository.NewsRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewmodel(private val newsRepository: NewsRepository):ViewModel() {

    val businessList : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val sportsList : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val technologyList : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    init {
        getBusinessNews()
        getSportsNews()
        getTechnologyNews()
    }

    // function for request.
    private fun getBusinessNews() = viewModelScope.launch {
        businessList.postValue(Resource.Loading())
        val response = newsRepository.getBusinessNews()
        businessList.postValue(handleBreakingNewsResponse(response))
    }

    // function for request.
    private fun getSportsNews() = viewModelScope.launch {
        sportsList.postValue(Resource.Loading())
        val response = newsRepository.getSportsNews()
        sportsList.postValue(handleSportsNewsResponse(response))
    }

    // function for request.
    private fun getTechnologyNews() = viewModelScope.launch {
        technologyList.postValue(Resource.Loading())
        val response = newsRepository.getTechnology()
        technologyList.postValue(handleTechnologyNewsResponse(response))
    }

    // function for checking if response is success or not.
    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    // function for checking if response is success or not.
    private fun handleSportsNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    // function for checking if response is success or not.
    private fun handleTechnologyNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}