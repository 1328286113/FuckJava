package com.example.fuckjava.util

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import com.google.android.material.internal.ContextUtils
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*


/**
 * Created by apple on 2018/6/13.
 */
object TextUtil {
    fun utilTxtColor(
        view: TextView,
        str: String,
        start: Int,
        startColor: Int,
        endColor: Int
    ) {
        val spannableString = SpannableString(str)
        spannableString.setSpan(
            ForegroundColorSpan(startColor),
            0,
            start,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            ForegroundColorSpan(endColor),
            start,
            str.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        view.text = spannableString
    }

    fun <T> swap2(
        list: List<T?>?,
        oldPosition: Int,
        newPosition: Int
    ) {
        checkNotNull(list) { "The list can not be empty..." }

        // 向前移动，前面的元素需要向后移动
        if (oldPosition < newPosition) {
            for (i in oldPosition until newPosition) {
                Collections.swap(list, i, i + 1)
            }
        }

        // 向后移动，后面的元素需要向前移动
        if (oldPosition > newPosition) {
            for (i in oldPosition downTo newPosition + 1) {
                Collections.swap(list, i, i - 1)
            }
        }
    }

    fun getTextViewStr(textview: TextView): String {
        return if (TextUtils.isEmpty(textview.text)) {
            ""
        } else {
            textview.text.toString()
        }
    }

    fun makeTime(t: String): String {
        return t.substring(0, 16).replace("T", " ")
    }

    fun getJson(fileName: String?): String {
        //将json数据变成字符串
        val stringBuilder = StringBuilder()
        try {
            //获取assets资源管理器
            val assetManager = Resources.getSystem().assets
            //通过管理器打开文件并读取
            val bf = BufferedReader(
                InputStreamReader(
                    assetManager.open(fileName)
                )
            )
            var line: String?
            while (bf.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return stringBuilder.toString()
    }

}