package com.rain.barter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rain.barter.ui.login.view.LoginActivity
import com.rain.barter.ui.registration.view.RegistrationActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUp()
    }

    private fun setUp(){
        FirebaseApp.initializeApp(this)

        CoroutineScope(Dispatchers.Main).launch {
            if(checkUser()) {
                delay(1000)
                startActivity(Intent(this@MainActivity, HomeActivity::class.java))
            } else {
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            }
        }

    }

    private suspend fun checkUser() : Boolean{
        return FirebaseAuth.getInstance().currentUser != null
    }
}
