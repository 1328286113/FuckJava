package com.example.fuckjava.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.fuckjava.R
import com.example.fuckjava.databinding.ActivityLogintestBinding
import com.example.fuckjava.model.LoginBean

class LoginActivity : AppCompatActivity(), View.OnClickListener{
    lateinit var lbean: LoginBean
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var activityLoginBinding: ActivityLogintestBinding = DataBindingUtil.setContentView(this, R.layout.activity_logintest)
        lbean = LoginBean("普京","123456")
        activityLoginBinding.lbean = lbean
        activityLoginBinding.userHandler = UserHandler(lbean)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_login -> {
                Toast.makeText(this,"name:${lbean.name}\npwd:${lbean.pwd}",Toast.LENGTH_SHORT).show()
            }
        }
    }

    class UserHandler(var loginBean: LoginBean){
        fun changeName(){
            loginBean.name = "短笛大魔王"
        }
        fun changePwd(){
            loginBean.pwd = "456789"
        }
    }


}