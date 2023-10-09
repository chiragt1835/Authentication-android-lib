package com.tot.authenticationmodule.utils

import com.tot.authenticationmodule.data.local.UserData

interface UserCallBack {

    fun onSuccess(userData: UserData)

    fun onError()

}