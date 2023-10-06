package com.tot.authenticationmodule.callback

import com.tot.authenticationmodule.model.UserResponse

interface UserCallBack {

    fun onSuccess(userResponse: UserResponse)

    fun onError()

}