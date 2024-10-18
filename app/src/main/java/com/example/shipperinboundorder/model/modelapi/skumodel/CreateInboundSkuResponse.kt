package com.example.shipperinboundorder.model.modelapi.skumodel

data class CreateInboundSkuResponse(
    val `data`: List<Sku>,
    val response: String,
    val responseCode: Int,
    val status: String
)