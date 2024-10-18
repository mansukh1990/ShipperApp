package com.example.shipperinboundorder.model

 data class UploadPod(
    val image: Int
)


data class GrnModel (
    val batchCode: String,
    val skuName: String,
    val barcode: String,
    val quantity: String,
    val expiryDate: String,
    val temp: String,
    var expand: Boolean = false,
    val uploadPodList : List<UploadPod>
)