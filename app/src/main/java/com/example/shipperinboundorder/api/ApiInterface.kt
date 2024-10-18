package com.example.shipperinboundorder.api

import com.example.shipperinboundorder.model.modelapi.inboundorderskulist.SkuRequest
import com.example.shipperinboundorder.model.modelapi.inboundorderskulist.SkuResponse
import com.example.shipperinboundorder.model.modelapi.inbundorderCreate.InboundOrderCreateRequest
import com.example.shipperinboundorder.model.modelapi.inbundorderCreate.InboundOrderCreateResponse
import com.example.shipperinboundorder.model.modelapi.login.LoginRequest
import com.example.shipperinboundorder.model.modelapi.login.LoginResponse
import com.example.shipperinboundorder.model.modelapi.skumodel.CreateInboundSkuResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {

    @POST("api/customer/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("api/customer/warehouse/inbound-order-sku-list")
    fun getSkus(
        @Header("Authorization") token: String,
        @Body request: SkuRequest): Call<SkuResponse>

    @POST("api/customer/warehouse/inbound-order-store")
    fun createInboundOrder(
        @Header("Authorization") token: String,
        @Body request: InboundOrderCreateRequest): Call<InboundOrderCreateResponse>
}