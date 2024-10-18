package com.example.shipperinboundorder.model.modelapi.login

data class LoginResponse(
    val `data`: Data,
    val response: String,
    val responseCode: Int,
    val status: String
)