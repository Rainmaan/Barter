package com.rain.barter.ui.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseUser
import com.rain.barter.HomeActivity
import com.rain.barter.R
import com.rain.barter.ViewModelFactory
import com.rain.barter.data.firebase.FirebaseHelper
import com.rain.barter.ui.login.viewmodel.LoginViewModel
import com.rain.barter.ui.registration.view.RegistrationActivity
import com.rain.barter.ui.registration.viewmodel.RegistrationViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_registration.email_edit_text
import kotlinx.android.synthetic.main.activity_registration.password_edit_text
import kotlinx.android.synthetic.main.activity_registration.progressBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setUpViewModel()
        setUpUI()

        loginViewModel.userMutableLiveData.observe(this, Observer<FirebaseUser> {
            if (it != null) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        })
    }

    private fun setUpViewModel() {
        val firebaseHelper = FirebaseHelper(application)
        loginViewModel = ViewModelProvider(this, ViewModelFactory(firebaseHelper)).get(
            LoginViewModel::class.java
        )
    }

    private fun setUpUI() {
        sign_in_btn.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                progressBar.visibility = View.VISIBLE
                loginViewModel.signInUser(
                    email_edit_text.text.toString().trim(),
                    password_edit_text.text.toString().trim()
                )
                delay(2000)
                progressBar.visibility = View.GONE
            }
        }

        go_to_register.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegistrationActivity::class.java))
        }
    }
}
