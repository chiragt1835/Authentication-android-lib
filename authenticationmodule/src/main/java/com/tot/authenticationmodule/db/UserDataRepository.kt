package com.tot.authenticationmodule.db

import androidx.annotation.WorkerThread
import com.tot.authenticationmodule.data.local.UserData

class UserDataRepository(private val userDao: UserDao?) {


    @WorkerThread
    suspend fun insert(userData: UserData): Long? {
      return userDao?.insert(userData)
    }
//
//    @WorkerThread
//    suspend fun update(videoData: VideoLocalData) {
//        wordDao?.updateFileData(videoData)
//    }


    @WorkerThread
    suspend fun getUserByEmailID(emailId : String) : UserData? {
        return userDao?.getUserData(emailId)
    }


//    @WorkerThread
//    suspend fun getList() : List<VideoLocalData>? {
//      return wordDao?.getList()
//    }


}