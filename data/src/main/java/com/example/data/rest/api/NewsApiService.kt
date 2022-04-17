package com.example.data.rest.api

import com.example.data.entity.news.NewsEntity
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.domain.interactor.result.Result

interface NewsApiService {

    @GET("/v2/top-headlines")
    fun getBreakingNews(
        @Query("country")
        countryCode: String? = "US",
        @Query("page")
        page: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Result<NewsEntity>

    @GET("/v2/everything")
    fun searchNews(
        @Query("q")
        searchQuery: String?,
        @Query("page")
        page: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Result<NewsEntity>

    companion object {
        const val API_KEY = "6747b1a8c9044983b2324f05df41a25f"
    }

}