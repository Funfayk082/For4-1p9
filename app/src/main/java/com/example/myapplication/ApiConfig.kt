package com.example.myapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ApiConfig {
    companion object {
        fun getApiService(): RetrofitApi {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create()
        }
    }
}