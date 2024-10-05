package com.example.myapplication

data class Response(
    val page: Int,
    val perPage: Int,
    val total: Int,
    val totalPages: Int,
    val data: List<DataDto>
)

data class DataDto(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
)
