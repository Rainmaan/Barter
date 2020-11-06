package com.rain.barter.data.firebase

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class FirebaseHelper() {
    private lateinit var application: Application
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var userMutableLiveData: MutableLiveData<FirebaseUser>
    private lateinit var firestore: FirebaseFirestore
    private lateinit var usersReference: CollectionReference

    constructor(application: Application): this(){
        this.application = application
        firebaseAuth = Firebase.auth
        userMutableLiveData = MutableLiveData()
        firestore = FirebaseFirestore.getInstance()
        usersReference = firestore.collection("Users")
    }

    fun registerUser(firstName: String, lastName: String, email: String, password: String){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    userMutableLiveData.postValue(firebaseAuth.currentUser)
                    addUserToFirestore(firstName, lastName, email)

                } else {
                    Toast.makeText(application, it.exception.toString(), Toast.LENGTH_SHORT)
                }
            }
    }

    private fun addUserToFirestore(firstName : String, lastName : String, email: String){
        var newUser = hashMapOf(
            "first_name" to firstName,
            "last_name" to lastName,
            "email" to email,
            "date_registered" to Timestamp.now(),
            "date_last_logged_in" to Timestamp.now(),
            "date_updated" to Timestamp.now()
        )

        usersReference.document(firebaseAuth.currentUser!!.uid).set(newUser)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(application, "User Registered!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(application, it.exception!!.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    suspend fun signInUser(email: String, password: String){
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful) {
                    userMutableLiveData.postValue(firebaseAuth.currentUser)
                    Toast.makeText(application, "Signed In!", Toast.LENGTH_SHORT)
                } else {
                    Toast.makeText(application, it.exception.toString(), Toast.LENGTH_SHORT)
                }
            }
    }

}