package com.tot.authenticationmodule.model

data class UserResponse(
    val userID : Int,
    val firstName : String,
    val lastName : String,
    val emailId : String,
    val phoneNumber : String
)
