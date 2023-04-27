package com.example.scoutonew

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object dataobject {

private val obj: Retrofit by lazy {
    Retrofit.Builder().baseUrl("https://vpic.nhtsa.dot.gov/api/vehicles/").addConverterFactory(GsonConverterFactory.create()).build()
}
    val newobj: dataInterface by lazy {
        obj.create(dataInterface::class.java)
    }
}

