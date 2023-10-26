package com.twitter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity2 : AppCompatActivity() {
    val provider = OAuthProvider.newBuilder("twitter.com")
    private lateinit var firebaseAuth: FirebaseAuth
    private val activity = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        findViewById<Button>(R.id.button).setOnClickListener {
            firebaseAuth=Firebase.auth
            val pendingResultTask = firebaseAuth.pendingAuthResult
            if (pendingResultTask != null) {
                // There's something already here! Finish the sign-in for your user.
                pendingResultTask
                    .addOnSuccessListener {
                        // User is signed in.
                        // IdP data available in
                        // authResult.getAdditionalUserInfo().getProfile().
                        // The OAuth access token can also be retrieved:
                        // ((OAuthCredential)authResult.getCredential()).getAccessToken().
                        // The OAuth secret can be retrieved by calling:
                        // ((OAuthCredential)authResult.getCredential()).getSecret().
                    }
                    .addOnFailureListener {
                        // Handle failure.
                    }
            } else {
                // There's no pending result so you need to start the sign-in flow.
                // See below.
                firebaseAuth
                    .startActivityForSignInWithProvider(activity, provider.build())
                    .addOnSuccessListener {
                        // User is signed in.
                        // IdP data available in
                        // authResult.getAdditionalUserInfo().getProfile().
                        // The OAuth access token can also be retrieved:
                        // ((OAuthCredential)authResult.getCredential()).getAccessToken().
                        // The OAuth secret can be retrieved by calling:
                        // ((OAuthCredential)authResult.getCredential()).getSecret().
                        findViewById<TextView>(R.id.textView).setText(firebaseAuth.currentUser?.email)
                        Toast.makeText(applicationContext,"Success",Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener {
                        // Handle failure.
                        Toast.makeText(applicationContext,"Fail",Toast.LENGTH_LONG).show()
                    }
            }
        }
    }
}