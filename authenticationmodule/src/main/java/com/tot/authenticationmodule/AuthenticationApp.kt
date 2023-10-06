package com.tot.authenticationmodule

import android.app.Application
import android.content.Context
import android.content.Intent
import com.tot.authenticationmodule.actvity.LoginActivity
import com.tot.authenticationmodule.callback.UserCallBack

class AuthenticationApp  : Application() {


    companion object{
         var instance : AuthenticationApp? = null
    }

    var userCallBack: UserCallBack? = null

    fun startLoginActivity(context: Context, userCallBack: UserCallBack?){
        this.userCallBack = userCallBack
        val intent =  Intent(context,LoginActivity::class.java)
        context.startActivity(intent)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}