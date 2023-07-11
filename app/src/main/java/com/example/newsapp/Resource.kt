package com.example.newsapp

// This class handles the responses of the request.
sealed class Resource<T>(
    val data: T? = null, // Response<NewsResponse>.
    val message: String? = null) // Error message.
{
    class Success<T>(data: T?):Resource<T>(data)
    class Error<T>(message: String,data: T? = null):Resource<T>(data,message)
    class Loading<T>: Resource<T>()
}