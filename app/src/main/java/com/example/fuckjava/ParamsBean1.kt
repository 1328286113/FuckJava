package com.example.fuckjava

class ParamsBean1(var name: String) {
    var age: Int? = null

    init {
        name = "name:$name"
    }

    constructor(name: String, age: Int) : this(name) {
        this.age = age
    }

    fun bornYear(): Int = 2020 - this.age!!
    fun sayHi(name: String = "World") =
        print("Hi: $name")  //当name不传时默认为World，相当于重载了函数sayHi(name:String)

    fun sayHi(name: String = "World", age: Int) = print(name + "年龄：$age")

    fun rawString(){
        var name:String = "World"
        var myname:String = "普京"
        println("""
            |Hi!$name!
                |My name is $myname.
            """.trimMargin()) //去除"|"以及前面的空格
    }

    fun TestRange(){
        val range: IntRange = 0..10
        val range1: IntRange = 10 until 20
        for (i in range){
            print("$i,")
        }
        for (i in range1 step 2){
            print("$i,")
        }
    }
}