package com.example.milatronicsdashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.amplifyframework.core.Amplify
import com.example.milatronicsdashboard.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        sign_out_button.setOnClickListener{
            signOut()
        }
    }

    private fun signOut(){
        Amplify.Auth.signOut(
            { Log.i("AuthQuickstart", "Signed out successfully")
                startActivity(Intent(activity, LoginActivity::class.java))},
            { Log.e("AuthQuickstart", "Sign out failed", it) }
        )
    }
}