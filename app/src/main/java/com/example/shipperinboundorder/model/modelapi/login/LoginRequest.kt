package com.example.shipperinboundorder.model.modelapi.login

data class LoginRequest(
    val email: String,
    val password: String,
    val app_version: String="37",
)