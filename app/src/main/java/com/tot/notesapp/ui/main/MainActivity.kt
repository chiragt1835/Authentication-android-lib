package com.tot.notesapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.gson.Gson
import com.tot.authenticationmodule.AuthenticationApp
import com.tot.authenticationmodule.data.local.UserData
import com.tot.authenticationmodule.utils.Constant
import com.tot.authenticationmodule.utils.UserCallBack
import com.tot.notesapp.R
import com.tot.notesapp.databinding.ActivityMainBinding
import com.tot.notesapp.ui.notelist.NoteListActivity
import com.tot.notesapp.ui.utils.SharedPreference

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var sharedPreference: SharedPreference
    private var handler : Handler? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        splashScreen.setKeepOnScreenCondition{true}
        setContentView(binding.root)
        handler = Handler(Looper.getMainLooper())
        handler?.postDelayed({
            splashScreen.setKeepOnScreenCondition{false}
            initApp()
        },3000)

    }

    private fun initApp(){
        sharedPreference = SharedPreference(this)
        if (sharedPreference.getBoolean(Constant.IS_LOGIN)){
            val intent = Intent(this@MainActivity, NoteListActivity::class.java)
            startActivity(intent)
        }else{
            initAuthFLow()
        }
    }

    private fun initAuthFLow(){
        AuthenticationApp.instance?.startLoginActivity(this,  object : UserCallBack {
            override fun onSuccess(userData: UserData) {
                sharedPreference.setBoolean(Constant.IS_LOGIN, true)
                sharedPreference.setString(Constant.USER_DATA, Gson().toJson(userData))
                val intent = Intent(this@MainActivity, NoteListActivity::class.java)
                startActivity(intent)
            }

            override fun onError() {
            }
        })
    }


}