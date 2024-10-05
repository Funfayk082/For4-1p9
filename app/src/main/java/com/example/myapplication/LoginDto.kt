package com.example.myapplication

data class LoginDto(
    val email: String,
    val password: String
)

data class TokenDto(
    val token: String
)
