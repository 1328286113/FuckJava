package com.example.fuckjava.model

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.util.LogTime
import com.example.fuckjava.NewsID
import com.example.fuckjava.josnbean.Data
import com.example.fuckjava.josnbean.NewsBean
import com.example.fuckjava.network.RetrofitManager
import com.example.fuckjava.util.ResponseHandlerCoroutine
import kotlinx.coroutines.*
import java.util.logging.LogManager
import javax.security.auth.login.LoginException

class NewsViewModel : ViewModel() {
    val data: MutableLiveData<List<Data>> by lazy {
        MutableLiveData<List<Data>>().also {
            loadData()
        }
    }

    private fun loadData() {
        /**
         * 为应用中的每个 ViewModel 定义了 ViewModelScope。
         * 如果 ViewModel 已清除，则在此范围内启动的协程都会自动取消。
         * 如果您具有仅在 ViewModel 处于活动状态时才需要完成的工作，此时协程非常有用。
         * 例如，如果要为布局计算某些数据，则应将工作范围限定至 ViewModel，以便在 ViewModel 清除后，系统会自动取消工作以避免消耗资源。*/
        viewModelScope.launch(Dispatchers.Main) {
            println("launch")
//            try {
//                data.value = getNews()?.result?.data
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
            ResponseHandlerCoroutine.loadData<NewsBean> {
//                load {
//                    RetrofitManager.retrofitService.getNews(75, NewsID, "top")
//                }
                onNext {
                    data.value = it.result.data
                }
                onError {
                    it.printStackTrace()
                }
            }
        }
    }

//    private suspend fun getNews(): NewsBean? = withContext(Dispatchers.IO) {
//
//        return@withContext RetrofitManager.retrofitService.getNews(75, NewsID, "top")
//            .execute().body()
//    }


    val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
    }
}