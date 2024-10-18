package com.example.shipperinboundorder.model.modelapi.inbundorderCreate

data class InboundOrderCreateResponse(
    val `data`: List<Any>,
    val response: String,
    val responseCode: Int,
    val status: String
)