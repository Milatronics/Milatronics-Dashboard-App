package com.example.milatronicsdashboard.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.amplifyframework.core.Amplify
import com.example.milatronicsdashboard.R
import com.example.milatronicsdashboard.UserActivity
import com.example.milatronicsdashboard.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar

// The host activity for the login fragments.
class LoginActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityLoginBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Checks if the user is already signed in
        Amplify.Auth.fetchAuthSession(
            {
                Log.i("AuthFetchSession", "Auth session = $it")
                if(it.isSignedIn)
                    onSuccessfulSignIn()
            },
            {
                error -> Log.e("AuthFetchSession", "Failed to fetch auth session", error)
                Snackbar.make(binding.root, "No Network.", Snackbar.LENGTH_SHORT).show()
            }
        )

        // Inflates and sets the current view.
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Sets up the actionbar and navigation controller
        setSupportActionBar(binding.toolbar)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_content_login) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    // Gives functionality for navigating "back"
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    // Launches the User Activity on a successful sign in
    fun onSuccessfulSignIn(){
        Amplify.Auth.fetchUserAttributes(
            { attributes ->
                Log.i("AuthFetchUserAttributes", "SignIn successful. User attributes = $attributes")

                val intent = Intent(this, UserActivity::class.java)
                attributes.forEach{intent.putExtra(it.key.keyString, it.value)}
                startActivity(intent)
                finish()
            },
            {
                Log.e("AuthFetchUserAttributes", "Failed to fetch user attributes", it)
                Snackbar.make(binding.root, "No Network.", Snackbar.LENGTH_SHORT).show()
            }
        )
    }
}