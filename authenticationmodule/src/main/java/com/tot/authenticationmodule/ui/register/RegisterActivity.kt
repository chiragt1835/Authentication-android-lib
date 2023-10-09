package com.tot.authenticationmodule.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.tot.authenticationmodule.AuthenticationApp
import com.tot.authenticationmodule.R
import com.tot.authenticationmodule.data.local.UserData
import com.tot.authenticationmodule.databinding.ActivityRegisterBinding
import com.tot.authenticationmodule.utils.Validation

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var validation: Validation
    private val viewModel: RegisterViewModel by viewModels { VideoViewModelFactory((application as AuthenticationApp).repository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        validation = Validation(this)
        init()
    }

    private fun init(){
        initObservers()
        binding.btnRegister.setOnClickListener {
            registerUser()
        }

    }

    private fun initObservers() {
        viewModel.userData.observe(this){
            if (it == null){
                insertDataInDB()
            }else{
                Toast.makeText(this,
                    getString(R.string.this_email_id_is_already_exist_please_try_with_another_email_id), Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.isDataInsertedOrNot.observe(this){
            if (it?.toInt() == 1){
                Toast.makeText(this,
                    getString(R.string.register_successfully_and_now_you_can_login_with_credentials), Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun registerUser(){
        if (!validation.validateFirstName(binding.etFirstName.text.toString()) ) {
            return
        }else if (!validation.validateLastName(binding.etLastName.text.toString()) ) {
            return
        }else if (!validation.validatePhoneNo(binding.etMobileNumber.text.toString()) ) {
            return
        }else if (!validation.validateEmail(binding.etEmailNumber.text.toString()) ) {
            return
        }else if (!validation.validatePassword(binding.etPasswordNumber.text.toString()) ) {
            return
        }else if (!validation.validateConfirmPassword(binding.etPasswordNumber.text.toString(),binding.etConfirmPassword.text.toString()) ) {
            return
        }else{
            getDataFromDB()
        }
    }

    private fun getDataFromDB(){
        viewModel.getUserData(binding.etEmailNumber.text.toString())
    }

    private fun insertDataInDB(){
        viewModel.insert(UserData(
            firstName = binding.etFirstName.text.toString(),
            lastName = binding.etLastName.text.toString(),
            phoneNumber = binding.etMobileNumber.text.toString(),
            emailId = binding.etEmailNumber.text.toString(),
            password = binding.etPasswordNumber.text.toString()
        ))
    }

}