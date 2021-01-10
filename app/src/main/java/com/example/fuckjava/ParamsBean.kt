package com.example.fuckjava

class ParamsBean {
    @JvmField
    var id: String = ""
    var key: String = ""
    var type: String = ""
//    init {
//        id = "0"
//        key = ""
//        type = ""
//    }
    constructor()
    constructor(id: String, key: String, type: String) {  //参数默认为val，保证不被篡改
        this.id = id
        this.key = key
        this.type = type
    }


}