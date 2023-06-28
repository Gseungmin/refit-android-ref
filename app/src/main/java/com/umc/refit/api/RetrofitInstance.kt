package com.umc.refit.api

import com.google.gson.GsonBuilder
import com.umc.refit.API.LOCAL_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val okHttpClient: OkHttpClient by lazy {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
    }

    private val retrofit: Retrofit by lazy {

        val gson = GsonBuilder().setLenient().create()

        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient).baseUrl(LOCAL_BASE_URL).build()
    }

    /**member api*/
    val memberApi: MemberAPI by lazy {
        retrofit.create(MemberAPI::class.java)
    }
}
