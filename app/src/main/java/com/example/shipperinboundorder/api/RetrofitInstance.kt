package com.example.shipperinboundorder.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val retrofitBuilder: ApiInterface = Retrofit.Builder()
        .baseUrl("https://staging.celcius.in/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiInterface::class.java)

}