package com.example.milatronicsdashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.milatronicsdashboard.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(){
    private lateinit var binding: ActivityUserBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment_content_login) as NavHostFragment
            navController = navHostFragment.navController
//            supportFragmentManager
//                .beginTransaction()
//                .add(R.id.container, SignInFragment())
//                .commit()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

//    /**
//     * Navigate to the given fragment.
//     *
//     * @param fragment       Fragment to navigate to.
//     * @param addToBackstack Whether or not the current fragment should be added to the backstack.
//     */
//    fun navigateTo(fragment: Fragment, addToBackstack: Boolean) {
//        val transaction = supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.container, fragment)
//
//        if (addToBackstack) {
//            transaction.addToBackStack(null)
//        }
//
//        transaction.commit()
//    }
}