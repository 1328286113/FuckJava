package com.example.fuckjava.network

import com.example.fuckjava.josnbean.FilmBean
import com.example.fuckjava.josnbean.NewsBean
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Streaming
import retrofit2.http.Url

interface HttpApi {
    @GET
    @Streaming
    fun download(@Url url: String): Call<ResponseBody>

    @GET("/data/")
    fun getNews(@Query("id") id: Int, @Query("key") key: String, @Query("type") type: String): Call<NewsBean>

    @GET("/data/")
    fun searchFilm(@Query("id") id: Int, @Query("key") key: String, @Query("q") q: String):Call<FilmBean>
}