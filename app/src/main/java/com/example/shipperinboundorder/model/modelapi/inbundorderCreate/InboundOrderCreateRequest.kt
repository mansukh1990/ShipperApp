package com.example.shipperinboundorder.model.modelapi.inbundorderCreate

data class InboundOrderCreateRequest(
    val expected_delivery_date: Int,
    val external_order_no: Int,
    val quantity: List<Int>,
    val remarks: String,
    val sku_id: List<Int>,
    val warehouse: Int
)