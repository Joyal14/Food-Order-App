package com.example.foodorderapp.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

import android.widget.Toast

import androidx.activity.result.contract.ActivityResultContracts
import com.example.foodorderapp.MainActivity

import com.example.foodorderapp.R
import com.example.foodorderapp.databinding.ActivityLoginPageBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginPage : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var googleSignInClient: GoogleSignInClient

    private lateinit var loginBinding: ActivityLoginPageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        auth = Firebase.auth
        auth = FirebaseAuth.getInstance()

        //Google sign
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()

//        googleSignInClient = GoogleSignIn.getClient(this, gso)

        loginBinding.btnGoogleSignIn.setOnClickListener {
            signIn()
        }

        //lets get the users email and password
        loginBinding.btnLogin.setOnClickListener {
            checkAllField()
            performLogin()
        }

    }

    private fun checkAllField(): Boolean{
        if(loginBinding.emailEditText.length() == 0){
            loginBinding.emailEditText.error="This field is required"
            return false
        }
        if(loginBinding.passwordEditText.length() == 0){
            loginBinding.passwordEditText.error="This field is required"
            return false
        }
        return true
    }
    private fun performLogin() {
        //lets get the input from the users
        val email = loginBinding.emailEditText
        val password = loginBinding.passwordEditText

        if(email.text.isNullOrEmpty() || password.text.isNullOrEmpty()){
            Toast.makeText(this,"Please fill the all the details", Toast.LENGTH_SHORT)
                .show()
            return
        }
        val emailInput = email.text.toString()
        val passwordInput = password.text.toString()

        auth.signInWithEmailAndPassword(emailInput, passwordInput)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent =  Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
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
                Toast.makeText(this,"Error Occurred ${it.localizedMessage}",Toast.LENGTH_SHORT)
                    .show()
            }

        loginBinding.tvCreate.setOnClickListener {
            val intent = Intent(this, RegistationPage::class.java)
            startActivity(intent)
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleResults(task)
            }
        }

    //Handles the googlesign account f
    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful) {
            val account: GoogleSignInAccount? = task.result
            if (account != null) {
                updateUI(account)
            }
        } else {
            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_LONG).show()
        }
    }

    //If the Login is successful the page get to home screen
    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }
}