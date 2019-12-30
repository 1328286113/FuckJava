package com.example.fuckjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"
    var democlass: DemoClass = DemoClass()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        KArray()
//        KList()
//        KSet()
//        KMap()
        KSequence()
//        getData()
    }

    private fun getData() {
        RetrofitManager.retrofitService
    }

    val myonClick = object : View.OnClickListener {
        override fun onClick(v: View?) {

        }
    }


    private fun DemoFunction(pp: String): String {
        var p1 = DemoClass.a
        var p2 = DemoClass.A.a
        return p1.toString() + p2
    }

    private fun KArray() {
        //数组
        val strs: Array<String> = arrayOf("a", "b", "c")
//        val anys: Array<Any> = strs 不支持协变  与java相反
        Log.e(TAG, strs.toString())
        Log.e(TAG, strs.get(1))
        strs.set(1, "fuck")
        Log.e(TAG, strs.get(1))
        Log.e(TAG, (strs.contains("a")).toString())
        Log.e(TAG, strs.first())
//        Log.e(TAG, (strs.find { strs1 }).toString())
    }

    private fun KList() {
        val strs: List<String> = listOf("a", "b", "c")
        val anys: List<Any> = strs  //支持协变  与java相反
        Log.e(TAG, anys.toString())
    }

    private fun KSet() {
        val strSet = setOf("a", "b", "c")
        Log.e(TAG, strSet.toString())
    }

    private fun KMap() {
        val map = mapOf("k1" to 1, "k2" to "fuck", "k3" to 3.14)  //不可变集合
        map.toMutableMap() //转换成可变集合
        val mutablemap = mutableMapOf("k1" to 1, "k2" to "fuck", "k3" to 3.14)  //可变集合
        var value1 = map["k1"]
        Log.e(TAG, value1 as String)
        mutablemap["k1"] = 2
        value1 = mutablemap["k1"]
        Log.e(TAG, value1 as String)
    }

    private fun KSequence(){
        val sequence = sequenceOf("a","b","c")

        val list = listOf("aa","bb","cc")
        list.asSequence()

        val s = generateSequence(0) { it + 1 }
        Log.e(TAG, s.toString())
    }
}
