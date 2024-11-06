package com.example.shipperinboundorder.model.modelapi.orderlist

data class Order(
    val created_by_type: String,
    val customer_details: CustomerDetails,
    val customer_id: Int,
    val encrypted_id: String,
    val expected_delivery_date: String,
    val external_order_no: String,
    val id: Int,
    val is_sto_order: Int,
    val order_no: String,
    val order_status: OrderStatus,
    val order_type: String,
    val return_order_date: Any,
    val status: Int,
    val status_name: String,
    val sto_order_no: Any
)
