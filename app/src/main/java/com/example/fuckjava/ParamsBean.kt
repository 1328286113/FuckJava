package com.example.fuckjava

class ParamsBean {
    val id: String
    val key: String
    val type: String
//    init {
//        id = "0"
//        key = ""
//        type = ""
//    }
    constructor(id: String, key: String, type: String) {  //参数默认为val，保证不被篡改
        this.id = id
        this.key = key
        this.type = type
    }


}