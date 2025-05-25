package com.uax.androidmaster.primeraapp.ui.login

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.uax.androidmaster.primeraapp.NavigationWrapper
import com.uax.androidmaster.primeraapp.ui.initial.InitialScreen

class FirstActivity : AppCompatActivity() {

    private lateinit var navHostController: NavHostController
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()
        supportActionBar?.hide()

        setContent {
            navHostController = rememberNavController()

            Surface(
                modifier = Modifier.Companion.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                NavigationWrapper(navHostController, auth, db)

            }
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = auth.currentUser

        if(currentUser!= null){
            Log.i("Estoy Logao", "SI")

        }
    }
}