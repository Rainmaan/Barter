package com.rain.barter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rain.barter.data.firebase.FirebaseHelper
import com.rain.barter.data.repository.login.LoginRepository
import com.rain.barter.data.repository.registration.RegisterRepository
import com.rain.barter.ui.login.viewmodel.LoginViewModel
import com.rain.barter.ui.registration.viewmodel.RegistrationViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory (private val firebaseHelper: FirebaseHelper) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {

            //RegistrationViewModel
            //start
            modelClass.isAssignableFrom(RegistrationViewModel::class.java) -> {
                RegistrationViewModel(RegisterRepository(firebaseHelper)) as T
            }
            //end

            //RegistrationViewModel
            //start
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(LoginRepository(firebaseHelper)) as T
            }
            //end

            else -> throw IllegalArgumentException("Class not found!")
        }
    }

}