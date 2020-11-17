package com.rain.barter.data.repository.login

import com.rain.barter.data.firebase.FirebaseHelper

class LoginRepository(private val firebaseHelper: FirebaseHelper) {

    val userMutableLiveData = firebaseHelper.userMutableLiveData

    suspend fun signInUser(email: String, password: String) = firebaseHelper.signInUser(email, password)
}