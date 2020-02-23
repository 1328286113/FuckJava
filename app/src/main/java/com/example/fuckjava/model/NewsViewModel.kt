package com.example.fuckjava.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fuckjava.NewsID
import com.example.fuckjava.josnbean.Data
import com.example.fuckjava.josnbean.NewsBean
import com.example.fuckjava.network.RetrofitManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel :ViewModel() {
    private val data:MutableLiveData<List<Data>> by lazy {
        MutableLiveData<List<Data>>().also{
            loadData()
        }
    }
    fun getData():LiveData<List<Data>>{
        return data
    }

    private fun loadData(){
        /**
         * 为应用中的每个 ViewModel 定义了 ViewModelScope。
         * 如果 ViewModel 已清除，则在此范围内启动的协程都会自动取消。
         * 如果您具有仅在 ViewModel 处于活动状态时才需要完成的工作，此时协程非常有用。
         * 例如，如果要为布局计算某些数据，则应将工作范围限定至 ViewModel，以便在 ViewModel 清除后，系统会自动取消工作以避免消耗资源。*/
        viewModelScope.launch(Dispatchers.Main) {
            println("launch")
            data.value = getNews()?.result?.data
        }
    }

    private suspend fun getNews(): NewsBean? = withContext(Dispatchers.IO) {
//        return@withContext RetrofitManager.retrofitService.getNews("http://zhouxunwang.cn/data/?id=75&key=Ub6U8YlnQo/+ipiJ94wxR2rIPgTgsJeZ/px16A&type=top")
        return@withContext RetrofitManager.retrofitService.getNews(75, NewsID,"top")
            .execute().body()
    }
}