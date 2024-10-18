package com.example.shipperinboundorder.model


data class QualityCheckModel(
    val batchCode: String,
    val skuName: String,
    val barcode: String,
    val damagedQty: String,
    val temp: String,
    var expand: Boolean = false,
    var uploadPodList: List<UploadPod>
)