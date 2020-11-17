package com.rain.barter.ui.login.viewmodel

import androidx.lifecycle.ViewModel
import com.rain.barter.data.repository.login.LoginRepository

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    val userMutableLiveData = loginRepository.userMutableLiveData

    suspend fun signInUser(email: String, password: String) = loginRepository.signInUser(email, password)
}