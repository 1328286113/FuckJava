package com.example.fuckjava.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fuckjava.NewsID
import com.example.fuckjava.R
import com.example.fuckjava.josnbean.NewsBean
import com.example.fuckjava.network.RetrofitManager
import kotlinx.android.synthetic.main.activity_launch.*
import kotlinx.coroutines.*

class LaunchActivity : AppCompatActivity() {

    val url: String =
        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1579422688577&di=e22644b1cada485c3abe9011358993a9&imgtype=0&src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farchive%2F97e8249a2c51e4df2f75bb696db6c9a71ec543a9.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

//        GlobalScope.launch(Dispatchers.Main) {
//            val bitmap = getbitmap()
////            val jsonBean = getNews()
//            val bit_1 = async {
//                delay(2000)
//                cropBitmap(bitmap,0,0,bitmap!!.width/2,bitmap.height/2) }
//            val bit_2 = async {
//                delay(4000)
//                cropBitmap(bitmap,bitmap!!.width/3*2,bitmap.height/3*2,bitmap.width/3,bitmap.height/3) }
//            iv.setImageBitmap(bitmap)
//            iv_0_25.setImageBitmap(bit_1.await())
//            iv_0_11.setImageBitmap(bit_2.await())
////            tv.setText(jsonBean?.result?.data?.get(0)?.title)
//        }
    }

    private suspend fun getbitmap(): Bitmap? = withContext(Dispatchers.IO) {
        val responseBody = RetrofitManager.retrofitService.download(url).execute()
        if (responseBody.isSuccessful) {
            return@withContext BitmapFactory.decodeStream(responseBody.body()?.byteStream())
        }
        null
    }

//    private suspend fun getNews(): NewsBean? = withContext(Dispatchers.IO) {
//        return@withContext RetrofitManager.retrofitService.getNews(75, NewsID,"top")
//        return@withContext RetrofitManager.retrofitService.getNews("http://zhouxunwang.cn/data/?id=75&key=Ub6U8YlnQo/+ipiJ94wxR2rIPgTgsJeZ/px16A&type=top")
//            .execute().body()
//    }

    private fun cropBitmap(bitmap: Bitmap?, sX: Int, sY: Int, eX: Int, eY: Int): Bitmap =
        Bitmap.createBitmap(bitmap, sX, sY, eX, eY)
}

