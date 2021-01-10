package com.example.fuckjava.network

import com.example.fuckjava.network.converter.BitmapCovertFactory
import com.example.fuckjava.network.converter.JsonAndStringConverters
import com.example.fuckjava.network.converter.StringConvertFactory
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
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
    private val baseUrl = "http://zhouxunwang.cn/"
    val mHttpClient = OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(BaseUrlInterceptor())
        .addNetworkInterceptor(StethoInterceptor())
        .build()
    private var mRetrofitBuilder: Retrofit.Builder? = null
    val retrofitService: HttpApi
        get() {
            if (mRetrofitBuilder == null) {
                mRetrofitBuilder = Retrofit.Builder()
                    .addConverterFactory(BitmapCovertFactory.create())
                    .addConverterFactory(
                        JsonAndStringConverters.MulTypeConverterFactory(
                            GsonConverterFactory.create(),
                            StringConvertFactory.create()
                        )
                    )

                    .baseUrl(baseUrl)
                    .callFactory(object : CallFactoryProxy(mHttpClient){  //用于切换baseURL
                        override fun getNewUrl(baseUrlName: String?, request: Request?): HttpUrl? {
                            if (baseUrlName.equals("baidu")) {
                                val oldUrl = request!!.url().toString()
                                val newUrl = oldUrl.replace(
                                    "https://wanandroid.com/",
                                    "https://www.baidu.com/"
                                )
                                return HttpUrl.get(newUrl)
                            }
                            return null
                        }
                    })
                    .client(mHttpClient)
            }
            return mRetrofitBuilder!!.build().create(
                HttpApi::class.java
            )
        }
}
