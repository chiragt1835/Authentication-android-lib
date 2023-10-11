package com.tot.authenticationmodule.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.tot.authenticationmodule.data.local.UserData
import com.tot.authenticationmodule.db.UserDataRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val userDataRepository: UserDataRepository) : ViewModel() {


//    var list = MutableLiveData<List<VideoLocalData>>()


//    fun getFileList() = viewModelScope.launch {
//        list = MutableLiveData<List<VideoLocalData>>()
//        list.value = repository.getList()
//    }
//
//    fun insert(videoData: VideoLocalData) = viewModelScope.launch {
//        repository.insert(videoData)
//    }
//
//    fun update(videoData: VideoLocalData) = viewModelScope.launch {
//        repository.update(videoData)
//    }

    var userData = MutableLiveData<UserData>()

    fun getUserData(emailId : String) = viewModelScope.launch {
        userData.postValue(userDataRepository.getUserByEmailID(emailId))
    }


}

class LoginViewModelFactory(private val userDataRepository: UserDataRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(userDataRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}



