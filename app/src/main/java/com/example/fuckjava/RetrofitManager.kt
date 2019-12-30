package com.example.fuckjava

import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import retrofit2.Retrofit

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
                    .baseUrl(baseUrl)
                    .client(
                        mHttpClientBuilder
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .writeTimeout(15, TimeUnit.SECONDS)
                            .readTimeout(10, TimeUnit.SECONDS)
                            .build()
                    )
            }
            return mRetrofitBuilder!!.build().create(HttpApi::class.java)
        }
}
