package com.example.milatronicsdashboard.login

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.amplifyframework.auth.AuthProvider
import com.amplifyframework.core.Amplify
import com.example.milatronicsdashboard.R
import com.example.milatronicsdashboard.databinding.FragmentSignInBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.android.synthetic.main.fragment_sign_in.view.*


// Fragment representing the login screen for the users
class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.root.sign_in_button.setOnClickListener {
            signIn()
        }
        binding.root.google_sign_in_button.setOnClickListener {
            googleSignIn()
        }
        binding.root.sign_up_now.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
        binding.root.forgot_password.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_forgotPasswordFragment)
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
            password_text_input_sign_in.error = null
            Amplify.Auth.signIn(email_text_edit_text.text.toString().trim(), password_edit_text_sign_in.text.toString(),
                { result ->
                    if (result.isSignInComplete) {
                        (activity as LoginActivity).onSuccessfulSignIn()
                    } else {
                        Log.i("AuthSignIn", "Sign in not complete")
                        Snackbar.make(binding.root, "Sign-In Failed", Snackbar.LENGTH_SHORT).show()
                    }
                },
                {
                    Log.e("AuthSignIn", "Failed to sign in", it)
                    Snackbar.make(binding.root, "Username/Password is incorrect", Snackbar.LENGTH_SHORT).show()
                }
            )
        }
    }

    private fun googleSignIn(){
        Amplify.Auth.signInWithSocialWebUI(
            AuthProvider.google(), activity as Activity,
            {
                (activity as LoginActivity).onSuccessfulSignIn()
            },
            {
                Log.e("AuthGoogleSignIn", "Sign in failed", it)
                Snackbar.make(binding.root, "Google Sign in failed. Try Again.", Snackbar.LENGTH_SHORT).show()
            }
        )
    }

    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }
}