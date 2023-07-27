package com.example.foodorderapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.foodorderapp.R
import com.example.foodorderapp.databinding.ActivityLoginPageBinding
import com.example.foodorderapp.databinding.ActivityRegistationPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistationPage : AppCompatActivity() {

    private lateinit var registationBinding: ActivityRegistationPageBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registationBinding = ActivityRegistationPageBinding.inflate(layoutInflater)
        setContentView(registationBinding.root)

        auth = Firebase.auth

        registationBinding.tvCreate.setOnClickListener {
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
        }

        registationBinding.btnLogin.setOnClickListener {
            performSignIn()
        }

    }

    private fun performSignIn() {
        val email = registationBinding.emailEditText.text.toString()
        val password = registationBinding.passwordEditText.text.toString()
        val cpassword = registationBinding.cfpasswordEditText.text.toString()

        if(email.isNotEmpty() || password.isNotEmpty() || cpassword.isNotEmpty()) {
            if (password == cpassword) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val intent = Intent(this, LoginPage::class.java)
                            startActivity(intent)
                            Toast.makeText(
                                baseContext,
                                "Successful.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(
                                baseContext,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            this,
                            "Error Occurred ${it.localizedMessage}",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
            } else {
                Toast.makeText(this, "Password not matching", Toast.LENGTH_SHORT).show()
            }
        }
        else
        {
            Toast.makeText(this, "Please fill the all the details", Toast.LENGTH_SHORT).show()
        }

//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    val intent =  Intent(this,LoginPage::class.java)
//                    startActivity(intent)
//                    Toast.makeText(
//                        baseContext,
//                        "Successful.",
//                        Toast.LENGTH_SHORT,
//                    ).show()
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Toast.makeText(
//                        baseContext,
//                        "Authentication failed.",
//                        Toast.LENGTH_SHORT,
//                    ).show()
//                }
//            }
//            .addOnFailureListener {
//                Toast.makeText(this,"Error Occurred ${it.localizedMessage}", Toast.LENGTH_SHORT)
//                    .show()
//            }
    }
}