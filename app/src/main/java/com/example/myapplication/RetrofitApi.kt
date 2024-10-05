package com.example.myapplication

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitApi {
    @GET("/api/users")
    fun getUsers(): Call<Response?>

    @POST("/api/login")
    fun login(@Body loginDto: LoginDto): Call<TokenDto?>
}