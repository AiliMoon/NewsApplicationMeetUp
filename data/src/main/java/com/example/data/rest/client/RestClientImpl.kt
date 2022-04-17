package com.example.data.rest.client

import com.example.data.rest.api.NewsApiService
import com.example.data.rest.callAdapter.ResultCallAdapterFactory
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://newsapi.org"
class RestClientImpl(
) : RestClient {
    private val retrofit: Retrofit

    companion object {
        private const val DEFAULT_TIME_IN_SECOND: Long = 60
    }

    private val httpClient =
        OkHttpClient.Builder()
            .readTimeout(DEFAULT_TIME_IN_SECOND, TimeUnit.SECONDS)
            .connectTimeout(DEFAULT_TIME_IN_SECOND, TimeUnit.SECONDS)
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

    init {
        val gson = GsonBuilder().create()

        this.retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()
    }

    override fun newsApiService(): NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }
}