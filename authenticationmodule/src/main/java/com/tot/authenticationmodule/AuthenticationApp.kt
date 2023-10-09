package com.tot.authenticationmodule

import android.app.Application
import android.content.Context
import android.content.Intent
import com.tot.authenticationmodule.db.UserDataRepository
import com.tot.authenticationmodule.db.UserDatabase
import com.tot.authenticationmodule.ui.login.LoginActivity
import com.tot.authenticationmodule.utils.UserCallBack
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class AuthenticationApp  : Application() {


    private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { UserDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { UserDataRepository(database.userDao()) }


    var userCallBack: UserCallBack? = null

    fun startLoginActivity(context: Context, userCallBack: UserCallBack?){
        this.userCallBack = userCallBack
        val intent =  Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object{
        var instance : AuthenticationApp? = null
    }

}