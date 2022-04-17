package com.example.data.rest.client

import com.example.data.rest.api.NewsApiService

interface RestClient {
    fun newsApiService(): NewsApiService
}