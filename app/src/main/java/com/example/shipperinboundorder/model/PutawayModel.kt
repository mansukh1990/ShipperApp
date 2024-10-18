package com.example.shipperinboundorder.model


data class PutawayModel(
    val batchCode: String,
    val skuName: String,
    val barcode: String,
    val quantity: String,
    val chamber: String,
    val rack: String,
    val location: String,
    var expand: Boolean = false,
)