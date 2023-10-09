package com.tot.authenticationmodule.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.tot.authenticationmodule.AuthenticationApp
import com.tot.authenticationmodule.R
import com.tot.authenticationmodule.databinding.ActivityLoginBinding
import com.tot.authenticationmodule.ui.register.RegisterActivity
import com.tot.authenticationmodule.utils.Validation

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var validation: Validation
    private val viewModel: LoginViewModel by viewModels { VideoViewModelFactory((application as AuthenticationApp).repository) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        validation = Validation(this)
        init()
    }

    private fun init(){
        initObservers()
        binding.btnLogin.setOnClickListener {
            loginUser()
        }
        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun initObservers() {
        viewModel.userData.observe(this){
            if (it == null){
                Toast.makeText(this, getString(R.string.login_error_message), Toast.LENGTH_SHORT).show()
            }else{
                Log.e("TAG", "getDataFromDB: " + Gson().toJson(it) )
                if (binding.etPasswordNumber.text.toString() == it.password){
                    finish()
                    AuthenticationApp.instance?.userCallBack?.onSuccess(it)
                }else{
                    Toast.makeText(this,
                        getString(R.string.you_enter_wrong_password), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun loginUser(){
        if (!validation.validateEmail(binding.etEmailNumber.text.toString()) ) {
            return
        }else if (!validation.validatePassword(binding.etPasswordNumber.text.toString()) ) {
            return
        }else{
            getDataFromDB()
        }
    }

    private fun getDataFromDB(){
        viewModel.getUserData(binding.etEmailNumber.text.toString())
    }

}