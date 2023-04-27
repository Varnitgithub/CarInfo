package com.example.scoutonew

import retrofit2.Response
import retrofit2.http.*

interface dataInterface {
    @GET("getallmakes?format=json")
   suspend fun getdata(): Response<newmodel>


}