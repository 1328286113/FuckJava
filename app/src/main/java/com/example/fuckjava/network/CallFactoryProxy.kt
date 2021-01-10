package com.example.fuckjava.network

import androidx.annotation.Nullable
import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.Request


abstract class CallFactoryProxy(private val delegate: Call.Factory) : Call.Factory {

    companion object {
        private const val NAME_BASE_URL = "BaseUrlName"
    }

    override fun newCall(request: Request): Call {
        val baseUrlName: String? = request.header(NAME_BASE_URL)
        if (baseUrlName != null) {
            val newHttpUrl = getNewUrl(baseUrlName, request)
            if (newHttpUrl != null) {
                val newRequest: Request = request.newBuilder().url(newHttpUrl).build()
                return delegate.newCall(newRequest)
            } else {
//                Log.w(
//                    RetrofitFactory.TAG,
//                    "getNewUrl() return null when baseUrlName==$baseUrlName"
//                )
            }
        }
        return delegate.newCall(request)
    }

    @Nullable
    protected abstract fun getNewUrl(baseUrlName: String?, request: Request?): HttpUrl?

}