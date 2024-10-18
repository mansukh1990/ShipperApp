package com.example.shipperinboundorder.model


data class StatusLogModel(
    val statusBy: String,
    var orderStatus: Status,
    val timeStamp: String,
    val remarks: String,
    var expand: Boolean = false,
)
enum class Status{
    ORDERPLACED,
    ARRIVED,
    GRNPENDING,
    GRNINPROCESS,
    GRNCOMPLETED,
    QCPENDING,
    QCINPROCESS,
    QCCOMPLETED,
    PUTAWAYPENDING,
    PUTAWAYINPROCESS,
    PUTAWAYCOMPLETED,
    DISPATCHCOMPLETED,
    RETURN,
    RETURNORDER,
    MANIFEST,
    DISPATCH,
    PICKING,
    PICKINGCOMPLETED,
    RETURNCOMPLETED


}