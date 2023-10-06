package com.tot.notesapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tot.authenticationmodule.AuthenticationApp
import com.tot.authenticationmodule.actvity.LoginActivity
import com.tot.authenticationmodule.callback.UserCallBack
import com.tot.authenticationmodule.model.UserResponse

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AuthenticationApp.instance?.startLoginActivity(this,  object : UserCallBack{
            override fun onSuccess(userResponse: UserResponse) {
                Log.e("TAG", "onSuccess: " + userResponse.firstName )
            }

            override fun onError() {
            }
        })

    }





}