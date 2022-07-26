package com.example.milatronicsdashboard.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.amplifyframework.core.Amplify
import com.example.milatronicsdashboard.R
import com.example.milatronicsdashboard.databinding.FragmentForgotPasswordBinding
import com.google.android.material.snackbar.Snackbar

// Fragment representing the forgot password screen
class ForgotPasswordFragment : Fragment() {
    private lateinit var binding: FragmentForgotPasswordBinding

    // Inflates and sets the fragment view to fragment_forgot_password.xml
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set listeners for the buttons
        binding.resetButton.setOnClickListener {
            resetPassword()
        }
        binding.cancelButtonResetPassword.setOnClickListener {
            findNavController().navigate(R.id.action_forgotPasswordFragment_to_signInFragment)
        }
    }

    // Sends a password reset code to the user's email id.
    private fun resetPassword(){
        val email = binding.emailTextInputPasswordResetEditText.text.toString().trim()

        Amplify.Auth.resetPassword(email,
            {
                Log.i("AuthPasswordReset", "Password reset OK: $it")
                Snackbar.make(binding.root, "Password reset code sent to $email", Snackbar.LENGTH_SHORT).show()
                activity?.runOnUiThread{
                    findNavController().navigate(R.id.action_forgotPasswordFragment_to_confirmResetPasswordFragment)
                }
            },
            {
                Log.e("AuthPasswordReset", "Password reset failed: $it")
                Snackbar.make(binding.root, "Password reset failed.", Snackbar.LENGTH_SHORT).show()
            }
        )
    }
}