package com.rain.barter.ui.registration.viewmodel

import androidx.lifecycle.ViewModel
import com.rain.barter.data.repository.registration.RegisterRepository

class RegistrationViewModel(private val registerRepository: RegisterRepository) : ViewModel() {

    val userMutableLiveData = registerRepository.userMutableLiveData

    suspend fun registerUser(firstName: String, lastName: String, email: String, password: String) = registerRepository.registerUser(firstName, lastName, email, password)


}