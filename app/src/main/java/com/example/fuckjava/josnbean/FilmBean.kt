package com.example.fuckjava.josnbean

data class FilmBean (
    /**
     * reason : 查询成功
     * result : {"title":"上海滩","tag":"经典","act":"周润发 吕良伟 赵雅芝 刘丹 庄文清 汤镇业 林建明 欧阳佩珊 景黛音 黄新 陈立品 廖启智 曾楚霖 苗侨伟","year":"1980","rating":null,"area":"香港","dir":"招振强 / 霍耀良 / 谭锐铭 / 李耀明 / 刘仕裕","desc":"上海滩是香港无线电视在1980年制作的一套长25集的电视剧。故事以民国年间的上海为背景，描述上海帮会内的人物情仇和爱情故事。上海滩在香港播映后非常受欢迎，之后无线电视在同年拍摄了续集及第3集：上海滩龙虎斗。之后有后多以类似题材拍摄的电视及电影。1996年无线亦有曾重拍此剧，亦曾多次被搬上电影银幕。","cover":"http://p5.qhimg.com/t01bbbb86f7abe6c036.jpg","vdo_status":"play","playlinks":"","video_rec":"","act_s":[{"name":"周润发","url":null,"image":"http://p7.qhmsg.com/dmsmty/120_110_100/t013cfc869184fd7ce3.jpg"},{"name":"吕良伟","url":null,"image":"http://p3.qhmsg.com/dmsmty/120_110_100/t01f9a538c49c00963d.jpg"},{"name":"赵雅芝","url":null,"image":"http://p0.qhmsg.com/dmsmty/120_110_100/t01c33705c1369c8f64.jpg"},{"name":"刘丹","url":null,"image":"http://p1.qhmsg.com/dmsmty/120_110_100/t019c2d84a9d2b2b576.png"},{"name":"庄文清","url":null,"image":"http://p1.qhmsg.com/dmsmty/120_110_100/t01519bf81ca12e6db1.jpg"},{"name":"汤镇业","url":null,"image":"http://p9.qhmsg.com/dmsmty/120_110_100/t0114b4c60987435d74.jpg"}]}
     * error_code : 0
     */
    val reason: String,
    val result: ResultBean,
    val error_code: Int
)

data class ResultBean (
    /**
     * title : 上海滩
     * tag : 经典
     * act : 周润发 吕良伟 赵雅芝 刘丹 庄文清 汤镇业 林建明 欧阳佩珊 景黛音 黄新 陈立品 廖启智 曾楚霖 苗侨伟
     * year : 1980
     * rating : null
     * area : 香港
     * dir : 招振强 / 霍耀良 / 谭锐铭 / 李耀明 / 刘仕裕
     * desc : 上海滩是香港无线电视在1980年制作的一套长25集的电视剧。故事以民国年间的上海为背景，描述上海帮会内的人物情仇和爱情故事。上海滩在香港播映后非常受欢迎，之后无线电视在同年拍摄了续集及第3集：上海滩龙虎斗。之后有后多以类似题材拍摄的电视及电影。1996年无线亦有曾重拍此剧，亦曾多次被搬上电影银幕。
     * cover : http://p5.qhimg.com/t01bbbb86f7abe6c036.jpg
     * vdo_status : play
     * playlinks :
     * video_rec :
     * act_s : [{"name":"周润发","url":null,"image":"http://p7.qhmsg.com/dmsmty/120_110_100/t013cfc869184fd7ce3.jpg"},{"name":"吕良伟","url":null,"image":"http://p3.qhmsg.com/dmsmty/120_110_100/t01f9a538c49c00963d.jpg"},{"name":"赵雅芝","url":null,"image":"http://p0.qhmsg.com/dmsmty/120_110_100/t01c33705c1369c8f64.jpg"},{"name":"刘丹","url":null,"image":"http://p1.qhmsg.com/dmsmty/120_110_100/t019c2d84a9d2b2b576.png"},{"name":"庄文清","url":null,"image":"http://p1.qhmsg.com/dmsmty/120_110_100/t01519bf81ca12e6db1.jpg"},{"name":"汤镇业","url":null,"image":"http://p9.qhmsg.com/dmsmty/120_110_100/t0114b4c60987435d74.jpg"}]
     */
    val title: String,
    val tag: String,
    val act: String,
    val year: String,
    val rating: Any,
    val area: String,
    val dir: String,
    val desc: String,
    val cover: String,
    val vdo_status: String,
    val playlinks: String,
    val video_rec: String,
    val `act_s`: List<ActSBean>
)

data class ActSBean (
    /**
     * name : 周润发
     * url : null
     * image : http://p7.qhmsg.com/dmsmty/120_110_100/t013cfc869184fd7ce3.jpg
     */
    val name: String,
    val url: Any,
    val image: String
)