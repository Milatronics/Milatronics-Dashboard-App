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
import kotlinx.android.synthetic.main.fragment_confirm_sign_up.view.*


class ConfirmSignUpFragment : Fragment() {
    private lateinit var binding: FragmentConfirmSignUpBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentConfirmSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.root.confirmation_button.setOnClickListener {
            confirm()
        }
        binding.root.cancel_button_confirmation.setOnClickListener {
            findNavController().navigate(R.id.action_confirmSignUpFragment_to_signInFragment)
        }
    }

    private fun confirm(){
        // Confirm first
        val args: ConfirmSignUpFragmentArgs by navArgs()
        val email = args.email
        Amplify.Auth.confirmSignUp(
            email, binding.confirmationEditText.text.toString(),
            { result ->
                if (result.isSignUpComplete) {
                    Log.i("AuthQuickstart", "Confirm signUp succeeded")
                     findNavController().navigate(R.id.action_confirmSignUpFragment_to_signInFragment)
                } else {
                    Log.i("AuthQuickstart","Confirm sign up not complete")
                }
            },
            { Log.e("AuthQuickstart", "Failed to confirm sign up", it) }
        )
    }
}