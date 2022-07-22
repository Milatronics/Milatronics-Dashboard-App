package com.example.milatronicsdashboard

import android.app.Application
import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.Amplify
import kotlin.system.exitProcess

class Milatronics: Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize amplify.
        try {
            Amplify.addPlugin(AWSCognitoAuthPlugin())
            Amplify.configure(applicationContext)
            Log.i("AuthAmplify", "Initialized Amplify")
        } catch (error: AmplifyException) {
            Log.e("AuthAmplify", "Could not initialize Amplify", error)
            exitProcess(-1)
        }
    }
}