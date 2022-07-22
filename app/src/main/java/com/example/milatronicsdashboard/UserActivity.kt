package com.example.milatronicsdashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.amplifyframework.auth.AuthUserAttributeKey
import com.example.milatronicsdashboard.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(){
    private lateinit var binding: ActivityUserBinding
    private lateinit var navController: NavController
    lateinit var name: String
    lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_content_login) as NavHostFragment
        navController = navHostFragment.navController

        email = intent.getStringExtra(AuthUserAttributeKey.email().keyString).toString()
        name = intent.getStringExtra(AuthUserAttributeKey.name().keyString).toString()

//        Snackbar.make(binding.root, "Logged in as $name", Snackbar.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}