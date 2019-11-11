package com.example.android.projectiii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navController = Navigation.findNavController(this, R.id.main_fragment_container)
        setupActionBarWithNavController(this, navController)
        NavigationUI.setupWithNavController(bottom_nav, navController)
    }

    override fun onSupportNavigateUp() =
        Navigation.findNavController(this, R.id.main_fragment_container).navigateUp()
}
