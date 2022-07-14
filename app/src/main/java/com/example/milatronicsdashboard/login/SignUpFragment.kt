package com.example.milatronicsdashboard.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.milatronicsdashboard.R
import com.example.milatronicsdashboard.databinding.FragmentSignUpBinding
import kotlinx.android.synthetic.main.fragment_sign_up.view.*


class SignUpFragment : Fragment() {
    private var name: String? = null
    private var email: String? = null
    private var password: String? = null
    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.root.sign_up_button.setOnClickListener {
            signUp()
        }
        binding.root.cancel_button_sign_up.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }
    }

    private fun signUp(){
        // Sign up
//        name = binding.nameEditText.text.toString()
//        email = binding.emailEditTextInputSignUp.text.toString()
//        password = binding.passwordEditTextSignUp.text.toString()
//
//        if(name != null && email != null && password != null && password!!.length >= 8){
//            val options = AuthSignUpOptions.builder()
//                .userAttribute(AuthUserAttributeKey.name(), binding.nameEditText.text.toString())
//                .build()
//            Amplify.Auth.signUp(binding.emailEditTextInputSignUp.text.toString(), binding.passwordEditTextSignUp.text.toString(), options,
//                {
                    findNavController().navigate(R.id.action_signUpFragment_to_confirmSignUpFragment)
//                    Log.i("AuthQuickStart", "Sign up succeeded: $it") },
//                { Log.e ("AuthQuickStart", "Sign up failed", it) }
//            )
//        }

    }
}