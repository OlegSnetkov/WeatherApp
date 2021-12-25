package com.avtograv.weatherapp.di

import com.avtograv.weatherapp.data.remote.retrofit.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit


class NetworkModule {
    private val baseUrl = "https://api.openweathermap.org/"

    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }

    private val contentType = "application/json".toMediaType()

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .addNetworkInterceptor(loggingInterceptor)
        .addInterceptor(ApiKeyInterceptor())
        .build()


    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(json.asConverterFactory(contentType))
        .client(httpClient)


    private val retrofit = retrofitBuilder.build()

    val api: ApiService by lazy { retrofit.create(ApiService::class.java) }
}

class ApiKeyInterceptor : Interceptor {

    companion object {
        private const val APP_ID = "aeff0f626d4160211be7d9de79c2cca9"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val origin = chain.request()
        val urlBuilder = origin.url.newBuilder()
        val url = urlBuilder
            .addQueryParameter("appid", APP_ID)
            .build()
        val requestBuilder = origin.newBuilder()
            .url(url)
        val request = requestBuilder.build()

        return chain.proceed(request)
    }
}