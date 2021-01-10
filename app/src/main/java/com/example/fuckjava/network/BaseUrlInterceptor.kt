package com.example.fuckjava.network

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


public class BaseUrlInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // 获取request
        val request = chain.request()
        // 从request中获取原有的HttpUrl实例oldHttpUrl
        val oldHttpUrl = request.url() // 获取request的创建者builder
        // 获取request的创建者builder
        val builder: Request.Builder = request.newBuilder()
        // 从request中获取headers，通过给定的键url_name
        val headerValues = request.headers("BaseUrlName")
        if (headerValues.size > 0) {
            // 如果有这个header，先将配置的header删除，因此header仅用作app和okhttp之间使用
            builder.removeHeader("BaseUrlName")
            // 匹配获得新的BaseUrl
            val headerValue = headerValues[0]
            val newBaseUrl: HttpUrl? = if ("baidu" == headerValue) {
                HttpUrl.parse(BASE_URL_WEATHER)
            }else{
                oldHttpUrl
            }
            // 重建新的HttpUrl，修改需要修改的url部分
            val newFullUrl = oldHttpUrl
                    .newBuilder()
                // 更换网络协议
                .scheme(newBaseUrl?.scheme())
                // 更换主机名
                .host(newBaseUrl?.host())
                // 更换端口
                .port(newBaseUrl?.port()!!)
                .build();
            // 重建这个request，通过builder.url(newFullUrl).build()；
            // 然后返回一个response至此结束修改
            return chain.proceed(builder.url(newFullUrl).build())
        }
        return chain.proceed(request)
    }
}