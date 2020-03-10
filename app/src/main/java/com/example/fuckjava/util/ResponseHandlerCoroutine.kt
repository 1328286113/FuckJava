package com.example.fuckjava.util

import kotlinx.coroutines.*

class ResponseHandlerCoroutine<T> {
    private var onNext: (T) -> Unit = {}
    private var onError: (Throwable) -> Unit = {}
    private var onCompleted: () -> Unit = {}
    private var load: suspend () -> T? = { null }

    fun onNext(onNext: (T) -> Unit) {
        this.onNext = onNext
    }
    fun onError(onError: (Throwable) -> Unit) {
        this.onError = onError
    }
    fun onCompleted(onCompleted: () -> Unit) {
        this.onCompleted = onCompleted
    }
    fun load(load: suspend () -> T?) {
        this.load = load
    }
    fun doWork() {
        // MainScope().launch {} 如果是Android使用 MainScope()代替GlobalScope
        MainScope().launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    load.invoke()
                }
                if (result != null) {
                    onNext.invoke(result)
                }
                onCompleted.invoke()
            } catch (throwable: Throwable) {
                onError.invoke(throwable)
            }
        }
    }
    companion object {
        fun <T> loadData(block: ResponseHandlerCoroutine<T>.() -> Unit) {
            val responseHandler = ResponseHandlerCoroutine<T>()
            block(responseHandler)
            responseHandler.doWork()
        }
    }
}