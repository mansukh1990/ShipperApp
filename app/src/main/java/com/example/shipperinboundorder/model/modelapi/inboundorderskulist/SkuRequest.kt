package com.example.shipperinboundorder.model.modelapi.inboundorderskulist

data class SkuRequest(
    val exclude_sku_ids: List<Int> = emptyList(),
    val search: String,
    val warehouse: Int
)