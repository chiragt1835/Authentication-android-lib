package com.tot.authenticationmodule.db

import androidx.room.*
import com.tot.authenticationmodule.data.local.UserData
import com.tot.authenticationmodule.utils.Constant

@Dao
interface UserDao {

    @Insert
    suspend fun insert(userData: UserData) : Long
//
//    @Query("SELECT * FROM video_data")
//    suspend fun getList(): List<VideoLocalData>

    @Query("SELECT * FROM ${Constant.DB_TABLE_NAME} WHERE emailId LIKE :emailId")
    suspend fun getUserData(emailId: String): UserData?

//    @Update
//    suspend fun updateFileData(videoLocalData: VideoLocalData)
}