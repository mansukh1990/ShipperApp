package com.example.shipperinboundorder.model.modelapi.inboundorderskulist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sku(
    val name: String,
    val id: Int,
) : Parcelable
