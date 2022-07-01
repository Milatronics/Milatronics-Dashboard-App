package com.example.milatronicsdashboard

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.login_fragment.view.*

// Fragment representing the login screen for the users
class LoginFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
            // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.login_fragment, container, false)

        // Set an error if the password is less than 8 characters.
        view.next_button.setOnClickListener {
            if (!isPasswordValid(password_edit_text.text)) {
                password_text_input.error = getString(R.string.error_password)
            } else {
                password_text_input.error = null // Clear the error
                (activity as NavigationHost).navigateTo(UserFragment(), false) // Login to the user
            }
        }
        // Clear the error once more than 8 characters are typed.
        view.password_edit_text.setOnKeyListener { _, _, _ ->
            if (isPasswordValid(password_edit_text.text)) {
                password_text_input.error = null //Clear the error
            }
            false
        }
        return view
    }

    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }
}