package com.example.shipperinboundorder.model.modelapi.orderlist

data class Paginate(
    val current_page: Int,
    val first_page_url: String,
    val last_page: Int,
    val last_page_url: String,
    val next_page_url: String,
    val per_page: Int,
    val prev_page_url: String,
    val total: Int
)