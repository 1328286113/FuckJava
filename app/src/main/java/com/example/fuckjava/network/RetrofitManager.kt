package com.example.fuckjava.network

import com.example.fuckjava.network.converter.JsonAndStringConverters
import com.example.fuckjava.network.converter.StringConvertFactory
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author yk on 2018/7/9  15:34.
 * Email:763744747@qq.com
 */

object RetrofitManager {
    /**
     * 连接超时时间
     */
    private val CONNECT_TIMEOUT: Long = 8_000
    private val baseUrl = "http://zhouxunwang.cn/"
    private val mHttpClient = OkHttpClient.Builder()
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
    private var mRetrofitBuilder: Retrofit.Builder? = null
    val retrofitService: HttpApi
        get() {
            if (mRetrofitBuilder == null) {
                mRetrofitBuilder = Retrofit.Builder()
                    .addConverterFactory(
                        JsonAndStringConverters.MulTypeConverterFactory(
                            GsonConverterFactory.create(),
                            StringConvertFactory.create()
                        )
                    )
                    .baseUrl(baseUrl)
                    .client(
                        mHttpClient
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .writeTimeout(15, TimeUnit.SECONDS)
                            .readTimeout(10, TimeUnit.SECONDS)
                            .addNetworkInterceptor(StethoInterceptor())
                            .build()
                    )
            }
            return mRetrofitBuilder!!.build().create(
                HttpApi::class.java
            )
        }
}
