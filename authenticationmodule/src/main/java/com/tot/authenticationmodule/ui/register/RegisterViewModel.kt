package com.tot.authenticationmodule.ui.register

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.tot.authenticationmodule.R
import com.tot.authenticationmodule.data.local.UserData
import com.tot.authenticationmodule.db.UserDataRepository
import kotlinx.coroutines.launch

class RegisterViewModel(private val userDataRepository: UserDataRepository) : ViewModel() {

    var userData = MutableLiveData<UserData?>()
    var isDataInsertedOrNot = MutableLiveData<Long>()

    fun insert(userData: UserData) = viewModelScope.launch {
        isDataInsertedOrNot.postValue(userDataRepository.insert(userData))
    }

    fun getUserData(emailId: String) = viewModelScope.launch {
        userData.postValue(userDataRepository.getUserByEmailID(emailId))
    }


}

class RegisterViewModelFactory(private val userDataRepository: UserDataRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegisterViewModel(userDataRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}



