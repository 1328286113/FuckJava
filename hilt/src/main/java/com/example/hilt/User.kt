package com.example.hilt

import javax.inject.Inject

data class User constructor(var name: String, var age: Int) {
    @Inject constructor() : this("ss", 1000)
}