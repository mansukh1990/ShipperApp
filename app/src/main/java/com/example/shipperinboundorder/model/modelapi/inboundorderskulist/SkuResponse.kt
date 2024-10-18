package com.example.shipperinboundorder.model.modelapi.inboundorderskulist

import com.example.shipperinboundorder.model.modelapi.skumodel.Sku


data class SkuResponse(
    val `data`: List<com.example.shipperinboundorder.model.modelapi.inboundorderskulist.Sku>,
    val response: String,
    val responseCode: Int,
    val status: String
)