package com.vladimir_tsurko.ecommerse.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vladimir_tsurko.ecommerse.R
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.AuthViewModel
import com.vladimir_tsurko.ecommerse.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

//    private val navController by lazy{
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
//        navHostFragment.navController
//    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController = findNavController(R.id.fragmentContainerView)
        bottomNavigationView.setupWithNavController(navController)
    }

    fun hideBottomBar(){
        findViewById<BottomNavigationView>(R.id.bottom_navigation).visibility = View.GONE
    }

    fun showBottomBar(){
        findViewById<BottomNavigationView>(R.id.bottom_navigation).visibility = View.VISIBLE
    }

}