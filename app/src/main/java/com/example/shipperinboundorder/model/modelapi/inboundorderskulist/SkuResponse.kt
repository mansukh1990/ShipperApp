package com.example.shipperinboundorder.model.modelapi.inboundorderskulist


data class SkuResponse(
    val `data`: List<Sku>,
    val response: String,
    val responseCode: Int,
    val status: String
)