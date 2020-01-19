package com.example.fuckjava

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_launch.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LaunchActivity : AppCompatActivity() {

    val url:String = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1579422688577&di=e22644b1cada485c3abe9011358993a9&imgtype=0&src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farchive%2F97e8249a2c51e4df2f75bb696db6c9a71ec543a9.jpg"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        GlobalScope.launch (Dispatchers.Main){
            val bitmap = getbitmap()
            iv.setImageBitmap(bitmap)
        }
    }

    private suspend fun getbitmap(): Bitmap? = withContext(Dispatchers.IO){
        val responseBody = RetrofitManager.retrofitService.download(url).execute()
        if (responseBody.isSuccessful){
            return@withContext BitmapFactory.decodeStream(responseBody.body()?.byteStream())
        }
        null
    }
}

