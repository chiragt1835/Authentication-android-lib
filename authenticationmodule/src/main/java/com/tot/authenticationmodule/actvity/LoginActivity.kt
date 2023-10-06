package com.tot.authenticationmodule.actvity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.UserData
import com.tot.authenticationmodule.AuthenticationApp
import com.tot.authenticationmodule.R
import com.tot.authenticationmodule.callback.UserCallBack
import com.tot.authenticationmodule.databinding.ActivityLoginBinding
import com.tot.authenticationmodule.model.UserResponse

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root);

        binding.btnLogin.setOnClickListener {
            sendUserResponse()
        }
    }

    private fun sendUserResponse() {
        val userData = UserResponse(
            firstName = "Chirag",
            lastName = "vaghani",
            emailId = "vaghanichiragt@gmail.com",
            phoneNumber = "9714470952",
            userID = 1
        )

        AuthenticationApp.instance?.userCallBack?.onSuccess(userData)
        finish()
    }

//

}