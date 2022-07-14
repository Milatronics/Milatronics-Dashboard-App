package com.example.milatronicsdashboard.login

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.milatronicsdashboard.R
import com.example.milatronicsdashboard.databinding.FragmentSignInBinding
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.android.synthetic.main.fragment_sign_in.view.*

// Fragment representing the login screen for the users
class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set an error if the password is less than 8 characters.
        binding.root.sign_in_button.setOnClickListener {
            signIn()
        }
        binding.root.goto_sign_up_button.setOnClickListener {
            signUp()
        }
        // Clear the error once more than 8 characters are typed.
        binding.root.password_edit_text_sign_in.setOnKeyListener { _, _, _ ->
            if (isPasswordValid(password_edit_text_sign_in.text)) {
                password_text_input_sign_in.error = null //Clear the error
            }
            false
        }
    }

    private fun signIn(){
        if (!isPasswordValid(password_edit_text_sign_in.text)) {
            password_text_input_sign_in.error = getString(R.string.error_password)
        } else {
            password_text_input_sign_in.error = null // Clear the error
//                (activity as NavigationHost).navigateTo(UserFragment(), false) // Login to the user
        }
    }

    private fun signUp(){
        findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
    }

    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }
}