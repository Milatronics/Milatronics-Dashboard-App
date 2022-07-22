package com.example.milatronicsdashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.amplifyframework.core.Amplify
import com.example.milatronicsdashboard.databinding.FragmentUserBinding
import com.example.milatronicsdashboard.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : Fragment() {
    private lateinit var binding: FragmentUserBinding

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        sign_out_button.setOnClickListener{
            signOut()
        }
        textView.text = "Welcome " + (activity as UserActivity).name
    }

    private fun signOut(){
        Amplify.Auth.signOut(
            { Log.i("AuthSignUp", "Signed out successfully")
                startActivity(Intent(activity, LoginActivity::class.java))
                activity?.finish()},
            { Log.e("AuthSignUp", "Sign out failed", it) }
        )
    }
}