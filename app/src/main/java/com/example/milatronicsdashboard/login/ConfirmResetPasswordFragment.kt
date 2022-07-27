package com.example.milatronicsdashboard.login

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.amplifyframework.core.Amplify
import com.example.milatronicsdashboard.R
import com.example.milatronicsdashboard.databinding.FragmentConfirmResetPasswordBinding
import com.google.android.material.snackbar.Snackbar

// Fragment representing the Reset Password Confirmation screen
class ConfirmResetPasswordFragment : Fragment() {
    private lateinit var binding: FragmentConfirmResetPasswordBinding

    // Inflates and sets the fragment view to fragment_confirm_reset_password.xml
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentConfirmResetPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set listeners for the buttons
        binding.confirmationButtonResetPassword.setOnClickListener {
            resetPassword()
        }
        binding.cancelButtonResetPassword.setOnClickListener {
            findNavController().navigate(R.id.action_confirmResetPasswordFragment_to_signInFragment)
        }

        // Clear the password length error once more than 8 characters are typed.
        binding.newPasswordEditText.setOnKeyListener { _, _, _ ->
            if (isPasswordValid(binding.newPasswordEditText.text)) {
                binding.newPasswordTextInput.error = null //Clear the error
            }
            false
        }
    }

    // Verify the confirmation code and reset password
    private fun resetPassword(){
        val confirmationCode = binding.confirmationEditTextResetPassword.text.toString()
        val newPassword = binding.newPasswordEditText.text

        if (!isPasswordValid(newPassword)){
            binding.newPasswordTextInput.error = getString(R.string.error_password)
            return
        }

        Amplify.Auth.confirmResetPassword(newPassword.toString(), confirmationCode,
            {
                Log.i("AuthConfirmResetPassword", "New password confirmed")
                Snackbar.make(binding.root, "Password successfully reset.", Snackbar.LENGTH_SHORT).show()
                activity?.runOnUiThread{
                    findNavController().navigate(R.id.action_confirmResetPasswordFragment_to_signInFragment)
                }
            },
            {
                Log.e("AuthConfirmResetPassword", "Failed to confirm password reset", it)
                Snackbar.make(binding.root, "Password reset failed.", Snackbar.LENGTH_SHORT).show()
            }
        )
    }

    // Checks if the password is at least 8 characters long.
    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }
}