package com.example.foodorderapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodorderapp.R
import com.example.foodorderapp.databinding.ActivityLoginPageBinding
import com.example.foodorderapp.databinding.ActivityRegistationPageBinding

class RegistationPage : AppCompatActivity() {

    private lateinit var registationBinding: ActivityRegistationPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registationBinding = ActivityRegistationPageBinding.inflate(layoutInflater)
        setContentView(registationBinding.root)
        registationBinding.tvCreate.setOnClickListener {
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
        }
    }
}