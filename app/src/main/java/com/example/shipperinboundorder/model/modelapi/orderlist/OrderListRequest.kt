package com.example.shipperinboundorder.model.modelapi.orderlist

data class OrderListRequest(
    val customer_id: String,
    val order_type: String,
    val search: String,
    val sort_key: String,
    val sort_order: String,
    val status: String,
    val warehouse: Int
)