package com.example.fuckjava.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import com.example.fuckjava.DemoClass
import com.example.fuckjava.ParamsBean1
import com.example.fuckjava.R
import com.example.fuckjava.network.RetrofitManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"
    var democlass: DemoClass =
        DemoClass()
    var paramsBean1: ParamsBean1 =
        ParamsBean1("普京大帝")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv.setOnClickListener(myonClick)
//        KArray()
//        KList()
//        KSet()
//        KMap()
        KSequence()
//        getData()
//        paramsBean1.sayHi()
//        paramsBean1.sayHi(age = 10)
//        paramsBean1.rawString()
//        paramsBean1.TestRange()
    }

    private fun getData() {
        RetrofitManager.retrofitService
    }

    val myonClick = object : View.OnClickListener {
        override fun onClick(v: View?) {
            when(v?.id){
                R.id.tv -> getData()
                else -> return
            }
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
        strs.forEach { i ->
            println("$i.")
        }

        for (element in strs) {
            println(element)
        }
//        Log.e(TAG, (strs.find { strs1 }).toString())
    }

    private fun KList() {
        val strs: List<String> = listOf("a", "b", "c")
        val anys: List<Any> = strs  //支持协变  与java相反
        Log.e(TAG, anys.toString())
        val filterstrs: List<String> = strs.filter { i ->
            i.equals("c")
        }

        val changestrs: List<String> = strs.map { i ->
            "fuck$i"
        }

        val flatstrs: List<Any> = strs.flatMap { i ->
            listOf(i, anys[0])
        }
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

    private fun KSequence() {
        val sequence = sequenceOf(1, 2, 3)

        val list = listOf("aa", "bb", "cc")
        list.asSequence()

        val s = generateSequence(0) { it + 1 }
        Log.e(TAG, s.toString())

        val chose = sequence
            .map { i ->
                println("Map $i")
                i * 2
            }.filter { i ->
                println("Filter $i")
                i % 4 == 0
            }
        print(chose.first()) //只有当chose被调用的时候才会循环执行.map & .filter 并且满足条件后不再循环
    }

    /**
     * 嵌套函数
     */
    fun login(name: String, pwd: String) {
        fun validate(value: String) {
            if (value.isEmpty()) {
                Toast.makeText(this, "请填写", LENGTH_SHORT).show()
            }
        }
        validate(name)
        validate(pwd)
    }
}
