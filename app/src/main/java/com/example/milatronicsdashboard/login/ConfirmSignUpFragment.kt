package com.example.milatronicsdashboard.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.amplifyframework.core.Amplify
import com.example.milatronicsdashboard.R
import com.example.milatronicsdashboard.databinding.FragmentConfirmSignUpBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_confirm_sign_up.view.*

// Fragment representing the Sign Up Confirmation screen
class ConfirmSignUpFragment : Fragment() {
    private lateinit var binding: FragmentConfirmSignUpBinding

    // Inflates and sets the fragment view to fragment_confirm_sign_up.xml
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentConfirmSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set listeners for the buttons
        binding.root.confirmation_button.setOnClickListener {
            confirmSignUp()
        }
        binding.root.cancel_button_confirmation.setOnClickListener {
            findNavController().navigate(R.id.action_confirmSignUpFragment_to_signInFragment)
        }
    }

    // Verify the email id provided for signing up
    private fun confirmSignUp(){
        val args: ConfirmSignUpFragmentArgs by navArgs()
        val email = args.emailId
        val confirmationCode = binding.confirmationEditText.text.toString()

        Amplify.Auth.confirmSignUp(
            email, confirmationCode,
            { result ->
                if (result.isSignUpComplete) {
                    Log.i("AuthConfirmSignUp", "Confirm signUp succeeded")
                    Snackbar.make(binding.root, "Sign up successful.", Snackbar.LENGTH_SHORT).show()
                    try {
                        findNavController().navigate(R.id.action_confirmSignUpFragment_to_signInFragment)
                    } catch (e: IllegalStateException) {
                        Log.e("AuthConfirmSignUp", "$e")
                    }
                }
                else {
                    Log.i("AuthConfirmSignUp","Confirm sign up not complete")
                    Snackbar.make(binding.root, "Confirm sign up not complete.", Snackbar.LENGTH_SHORT).show()
                }
            },
            {
                Log.e("AuthConfirmSignUp", "Failed to confirm sign up", it)
                Snackbar.make(binding.root, "Failed to confirm sign up", Snackbar.LENGTH_SHORT).show()
            }
        )
    }
}