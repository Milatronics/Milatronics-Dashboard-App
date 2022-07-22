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


// Fragment representing the main login screen for the users
class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding

    // Inflates and sets the fragment view to fragment_sign_in.xml
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set listeners for the buttons
        binding.signInButton.setOnClickListener {
            signIn()
        }
        binding.googleSignInButton.setOnClickListener {
            googleSignIn()
        }
        binding.signUpNow.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
        binding.forgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_forgotPasswordFragment)
        }

        // Clear the password length error once more than 8 characters are typed.
        binding.passwordEditTextSignIn.setOnKeyListener { _, _, _ ->
            if (isPasswordValid(binding.passwordEditTextSignIn.text)) {
                binding.passwordEditTextSignIn.error = null //Clear the error
            }
            false
        }
    }

    // Confirms the password is valid and then attempts to sign in the user.
    private fun signIn(){
        if (!isPasswordValid(binding.passwordEditTextSignIn.text)) {
            binding.passwordEditTextSignIn.error = getString(R.string.error_password)
        }
        else {
            val email = binding.emailTextEditText.text.toString().trim()
            val password = binding.passwordEditTextSignIn.text.toString()

            Amplify.Auth.signIn(email, password,
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

    // Perform sign in using Google
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

    // Checks if the password is at least 8 characters long.
    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }
}