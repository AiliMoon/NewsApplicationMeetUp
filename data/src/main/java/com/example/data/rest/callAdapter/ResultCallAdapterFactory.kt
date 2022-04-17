package com.example.data.rest.callAdapter

import com.example.data.exception.ConnectionLostException
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import com.example.domain.interactor.result.Result

class ResultCallAdapterFactory private constructor() : CallAdapter.Factory() {

    companion object {
        @JvmStatic
        @JvmName("create")
        operator fun invoke() = ResultCallAdapterFactory()
        private val client = OkHttpClient()
    }

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (Result::class.java != getRawType(returnType)) {
            return null
        }

        if (returnType !is ParameterizedType) {
            throw IllegalStateException(
                "Result return type must be parameterized as Result<Foo> or Result<out Foo>"
            )
        }

        val responseType = getParameterUpperBound(0, returnType)
        return ResultCallAdapter<Any>(responseType)
    }

    private class ResultCallAdapter<T> constructor(
        private val responseType: Type
    ) : CallAdapter<T, Result<T>> {

        override fun responseType(): Type {
            return responseType
        }

        override fun adapt(call: Call<T>): Result<T> {
            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> Result.Success(response.body()!!)
                    false -> {
                        if(response.raw().code == 401) {
                            Result.Exception(HttpException(response))
                        }
                        Result.Exception(HttpException(response))
                    }
                }
            } catch (throwable: Throwable) {
                val urlBuilder = "https://clients1.google.com/generate_204".toHttpUrlOrNull()!!
                val request = Request.Builder()
                request.url(urlBuilder)
                try {
                    val response = client.newCall(request.build()).execute()
                    if (response.isSuccessful) {
                        Result.Exception(throwable)
                    } else {
                        Result.Exception(ConnectionLostException())
                    }
                } catch (throwable: Throwable) {
                    Result.Exception(ConnectionLostException())
                }
            }
        }
    }
}