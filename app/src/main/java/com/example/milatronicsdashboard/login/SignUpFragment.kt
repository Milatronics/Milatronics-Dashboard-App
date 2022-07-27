package com.example.milatronicsdashboard.login

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.amplifyframework.auth.AuthException
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.core.Amplify
import com.example.milatronicsdashboard.R
import com.example.milatronicsdashboard.databinding.FragmentSignUpBinding
import com.google.android.material.snackbar.Snackbar


// Fragment representing the SignUp screen
class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding

    // Inflates and sets the fragment view to fragment_sign_up.xml
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set listeners for the buttons
        binding.signUpButton.setOnClickListener {
            signUp()
        }
        binding.cancelButtonSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        // Clear the password length error once more than 8 characters are typed.
        binding.passwordEditTextSignUp.setOnKeyListener { _, _, _ ->
            if (isPasswordValid(binding.passwordEditTextSignUp.text)) {
                binding.passwordTextInputSignUp.error = null //Clear the error
            }
            false
        }
    }

    // Confirms whether the User Details are valid, and then attempts to sign up the user
    private fun signUp(){
        // Read the user details
        val name = binding.nameEditText.text.toString().trim()
        val email = binding.emailEditTextInputSignUp.text.toString().trim()
        val password = binding.passwordEditTextSignUp.text.toString()

        if(name == ""){
            Snackbar.make(binding.root, "Please enter your name.", Snackbar.LENGTH_SHORT).show()
            return
        }
        if(email == ""){
            Snackbar.make(binding.root, "Please enter your email id.", Snackbar.LENGTH_SHORT).show()
            return
        }
        if(password == ""){
            Snackbar.make(binding.root, "Please enter a password.", Snackbar.LENGTH_SHORT).show()
            return
        }
        if (!isPasswordValid(binding.passwordEditTextSignUp.text)){
            binding.passwordTextInputSignUp.error = getString(R.string.error_password)
            return
        }

        Log.i("AuthSignUp", "Name: $name, Email: $email, Password: $password")

        val options = AuthSignUpOptions.builder()
            .userAttribute(AuthUserAttributeKey.name(), name)
            .build()
        Amplify.Auth.signUp(email, password, options,
            {
                Log.i("AuthSignUp", "Sign up succeeded: $it")
                Snackbar.make(binding.root, "Verification code sent to $email.", Snackbar.LENGTH_SHORT).show()
                activity?.runOnUiThread{
                    val action = SignUpFragmentDirections.actionSignUpFragmentToConfirmSignUpFragment(emailId = email)
                    findNavController().navigate(action)
                }
            },
            {
                // Handle the different possible sign up failures
                try{
                    Log.e("AuthSignUp", "$it")
                    throw it
                }
                catch (e: AuthException.UsernameExistsException) {
                    Snackbar.make(binding.root, "Account already exists.", Snackbar.LENGTH_SHORT).show()
                    activity?.runOnUiThread{
                        val action = SignUpFragmentDirections.actionSignUpFragmentToConfirmSignUpFragment(emailId = email)
                        findNavController().navigate(action)
                    }
                }
                catch (e: AuthException.InvalidParameterException) {
                    Snackbar.make(binding.root, "Invalid email id", Snackbar.LENGTH_SHORT).show()
                }
                catch(e: AuthException){
                    Snackbar.make(binding.root, "Sign up failed. Try again", Snackbar.LENGTH_SHORT).show()
                }
            }
        )
    }

    // Checks if the password is at least 8 characters long.
    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }
}