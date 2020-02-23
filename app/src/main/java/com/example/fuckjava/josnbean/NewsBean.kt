package com.example.fuckjava.josnbean


data class NewsBean(
		var reason: String,// 成功的返回
		var result: Result,
		var error_code: Int// 0
)

data class Result(
		var stat: String,// 1
		var data: List<Data>
)

data class Data(
		var uniquekey: String,// c7806b9c6b3f16e7997f7abf74782e9c
		var title: String,// 全国除湖北以外新增病例连降8天
		var date: String,// 2020-02-12 10:21
		var category: String,// 头条
		var author_name: String,// 人民日报
		var url: String,// http://mini.eastday.com/mobile/200212102108344.html
		var thumbnail_pic_s: String// http://05imgmini.eastday.com/mobile/20200212/20200212102108_4c9b279e9a976fc875c0158867c38bde_1_mwpm_03200403.jpg
)