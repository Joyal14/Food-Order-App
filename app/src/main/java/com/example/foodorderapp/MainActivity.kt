package com.example.foodorderapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.foodorderapp.databinding.ActivityMainBinding
import com.example.foodorderapp.fragment.HomeFragment
import com.example.foodorderapp.fragment.SearchFragment
import com.example.foodorderapp.fragment.StoreFragment

class MainActivity : AppCompatActivity() {

    private val mainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)

        mainBinding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.botNavHome -> setFragmentToContainer(HomeFragment())

                R.id.botNavSearch -> setFragmentToContainer(SearchFragment())

                R.id.botNavStore -> setFragmentToContainer(StoreFragment())


                else -> setFragmentToContainer(HomeFragment())
            }
            true
        }
    }
    private fun setFragmentToContainer(fragment: Fragment ) {

        supportFragmentManager.beginTransaction()
            .replace(mainBinding.container.id, fragment)
//            //.setCustomAnimations(enter, exit, popEnter, popExit)
            .commit()
    }
}