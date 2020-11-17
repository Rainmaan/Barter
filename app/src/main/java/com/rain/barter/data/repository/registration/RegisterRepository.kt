package com.rain.barter.data.repository.registration

import com.rain.barter.data.firebase.FirebaseHelper

class RegisterRepository(private val firebaseHelper: FirebaseHelper) {

    val userMutableLiveData = firebaseHelper.userMutableLiveData

    suspend fun registerUser(firstName: String, lastName: String, email: String, password: String) = firebaseHelper.registerUser(firstName, lastName, email, password)

}