package com.example.milatronicsdashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.amplifyframework.auth.AuthUserAttributeKey
import com.example.milatronicsdashboard.databinding.ActivityUserBinding
import com.google.android.material.snackbar.Snackbar

// Host activity for the user fragments.
class UserActivity : AppCompatActivity(){
    private lateinit var binding: ActivityUserBinding
    private lateinit var navController: NavController
    lateinit var name: String
    lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get the user details
        email = intent.getStringExtra(AuthUserAttributeKey.email().keyString).toString()
        name = intent.getStringExtra(AuthUserAttributeKey.name().keyString).toString()

        // Inflate and set the view.
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Display a snackbar displaying the username of the current user.
        Snackbar.make(binding.snackbarLayout, "Logged in as $name", Snackbar.LENGTH_SHORT).show()

        // Initialize the navigation controller
        navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController

        // Set up the bottom navigation bar to use the NavController.
        binding.bottomNavigationBar.setupWithNavController(navController)
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}