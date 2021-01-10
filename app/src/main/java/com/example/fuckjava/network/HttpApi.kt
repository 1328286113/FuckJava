package com.example.fuckjava.network

import android.graphics.Bitmap
import com.example.fuckjava.josnbean.FilmBean
import com.example.fuckjava.josnbean.NewsBean
import retrofit2.Call
import retrofit2.http.*

interface HttpApi {
    @GET
    @Streaming
//    fun download(@Url url: String): Call<ResponseBody>
    suspend fun download(@Url url: String): Bitmap

    @GET("/data/")
    suspend fun getNews(@Query("id") id: Int, @Query("key") key: String, @Query("type") type: String): NewsBean

    @HEAD("BaseUrlName:baidu")
    @GET("/data/")
    fun searchFilm(@Query("id") id: Int, @Query("key") key: String, @Query("q") q: String):Call<FilmBean>
}