package com.example.fuckjava.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class LoginBean: BaseObservable{
    var name:String = ""
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }
    var pwd:String = ""
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.pwd)
        }

    constructor(name: String, pwd: String) : super() {
        this.name = name
        this.pwd = pwd
    }
}