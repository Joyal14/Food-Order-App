package com.example.foodorderapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodorderapp.databinding.ActivityLoginPageBinding

class LoginPage : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)
        loginBinding.tvCreate.setOnClickListener {
            val intent = Intent(this, RegistationPage::class.java)
            startActivity(intent)
        }
    }
}