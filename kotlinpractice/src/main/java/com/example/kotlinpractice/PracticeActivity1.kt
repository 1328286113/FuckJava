package com.example.kotlinpractice

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_practice1.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PracticeActivity1 : AppCompatActivity() {
    val scope = MainScope()
    val coroutinename = CoroutineName("test")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice1)
        textView.viewTreeObserver.addOnGlobalLayoutListener {  }
        val exceptionHandler: CoroutineContext =
            CoroutineExceptionHandler { _, throwable -> Log.e("pp",throwable.localizedMessage) }

        scope.launch(exceptionHandler) {
                throw NullPointerException()
            val data = getData()
            val processedData = async { processData(data) }
            // 练习内容：用协程让上面 ↑ 这两行放在后台执行，然后把代码截图贴到腾讯课堂的作业里
            textView.text = processedData.toString()
        }

    }

    // 耗时函数 1
    private suspend fun getData(): String {
        // 假设这个函数比较耗时，需要放在后台
        var s = ""
        withContext(Dispatchers.IO) {
            s = "hen_coder"
        }
        return s
    }

    // 耗时函数 2
    private suspend fun processData(data: String): String {
        // 假设这个函数也比较耗时，需要放在后台
        return data.split("_") // 把 "hen_coder" 拆成 ["hen", "coder"]
            .map { it.capitalize() } // 把 ["hen", "coder"] 改成 ["Hen", "Coder"]
            .reduce { acc, s -> acc + s } // 把 ["Hen", "Coder"] 改成 "HenCoder"
    }
}
