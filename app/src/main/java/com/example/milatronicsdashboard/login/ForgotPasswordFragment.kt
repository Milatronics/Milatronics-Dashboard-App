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
import kotlinx.android.synthetic.main.fragment_forgot_password.*
import kotlinx.android.synthetic.main.fragment_forgot_password.view.*


class ForgotPasswordFragment : Fragment() {
    private lateinit var binding: FragmentForgotPasswordBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.root.reset_button.setOnClickListener {
            reset()
        }
        binding.root.cancel_button_reset_password.setOnClickListener {
            findNavController().navigate(R.id.action_forgotPasswordFragment_to_signInFragment)
        }
    }

    private fun reset(){
        // Confirm first

        Amplify.Auth.resetPassword(email_text_input_password_reset_edit_text.text.toString().trim(),
            { Log.i("AuthPasswordReset", "Password reset OK: $it") },
            {error-> Log.e("AuthPasswordReset", "Password reset failed", error) }
        )
        findNavController().navigate(R.id.action_forgotPasswordFragment_to_signInFragment)
    }
}