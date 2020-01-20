package com.example.fuckjava.network

import com.example.fuckjava.HttpApi
import com.example.fuckjava.network.converter.JsonAndStringConverters
import com.example.fuckjava.network.converter.StringConvertFactory
import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author yk on 2018/7/9  15:34.
 * Email:763744747@qq.com
 */

object RetrofitManager {
    /**
     * 连接超时时间
     */
    private val CONNECT_TIMEOUT: Long = 8_000
    private val baseUrl = "http://zhouxunwang.cn/data/?id=75&key=Ub6U8YlnQo/+ipiJ94wxR2rIPgTgsJeZ/px16A&type=top"
    private val mHttpClientBuilder = OkHttpClient.Builder()
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
    private var mRetrofitBuilder: Retrofit.Builder? = null
    val retrofitService: HttpApi
        get() {
            if (mRetrofitBuilder == null) {
                mRetrofitBuilder = Retrofit.Builder()
                    .addConverterFactory(JsonAndStringConverters.MulTypeConverterFactory(GsonConverterFactory.create(),StringConvertFactory.create()))
                    .baseUrl(baseUrl)
                    .client(
                        mHttpClientBuilder
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .writeTimeout(15, TimeUnit.SECONDS)
                            .readTimeout(10, TimeUnit.SECONDS)
                            .build()
                    )
            }
            return mRetrofitBuilder!!.build().create(
                HttpApi::class.java)
        }
}
