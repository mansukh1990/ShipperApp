package com.example.shipperinboundorder.model.modelapi.orderlist

data class OrderListResponse(
    val `data`: List<Order>,
    val paginate: Paginate,
    val response: String,
    val responseCode: Int,
    val status: String
)