package com.rain.barter.ui.registration.view

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.api.ResourceProto.resource
import com.google.firebase.auth.FirebaseUser
import com.rain.barter.HomeActivity
import com.rain.barter.R
import com.rain.barter.ViewModelFactory
import com.rain.barter.data.firebase.FirebaseHelper
import com.rain.barter.data.repository.registration.RegisterRepository
import com.rain.barter.ui.login.view.LoginActivity
import com.rain.barter.ui.registration.viewmodel.RegistrationViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_registration.email_edit_text
import kotlinx.android.synthetic.main.activity_registration.password_edit_text
import kotlinx.android.synthetic.main.activity_registration.progressBar
import kotlinx.coroutines.*

class RegistrationActivity : AppCompatActivity() {

    private lateinit var registrationViewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        setUpViewModel()
        setUpUI()

        registrationViewModel.userMutableLiveData.observe(this, Observer<FirebaseUser>{
            if (it != null){
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        })
    }

    private fun setUpViewModel(){
        val firebaseHelper = FirebaseHelper(application)
        registrationViewModel = ViewModelProvider(this, ViewModelFactory(firebaseHelper)).get(RegistrationViewModel::class.java)
    }

    private fun setUpUI(){
        register_btn.setOnClickListener {
           CoroutineScope(Dispatchers.Main).launch{
               progressBar.visibility = View.VISIBLE
                registrationViewModel.registerUser(
                    first_name_edit_text.text.toString().trim(),
                    last_name_edit_text.text.toString().trim(),
                    email_edit_text.text.toString().trim(),
                    password_edit_text.text.toString().trim()
                )
               delay(2000)
               progressBar.visibility = View.GONE
            }
        }


        go_to_login.setOnClickListener {
            startActivity(Intent(this@RegistrationActivity, LoginActivity::class.java))
        }
    }

}
