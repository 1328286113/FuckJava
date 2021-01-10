package com.example.fuckjava.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class LoginBean(name: String, pwd: String) : BaseObservable() {
    var name:String = name
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }
    var pwd:String = pwd
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.pwd)
        }

}