package com.example.fuckjava.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BaseViewModel :ViewModel(){
    val currentName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}