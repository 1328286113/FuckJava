package com.example.fuckjava

import com.example.fuckjava.model.JsonBean
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

interface HttpApi {
    @GET
    @Streaming
    fun download(@Url url: String): Call<ResponseBody>

    @GET
    fun getNews(@Url url: String): Call<JsonBean>
}