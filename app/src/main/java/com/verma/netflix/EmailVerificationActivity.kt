package com.verma.netflix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.core.view.View


class EmailVerificationActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_verification)


        val paragraphText = "Congrutulations! We Just sent an email to \n "
        val paragraphTextView = findViewById<TextView>(R.id.paragraphTextView)
        paragraphTextView.text = paragraphText

        auth = FirebaseAuth.getInstance()


        // Check if the user is already signed in and email is verified
        val user = auth.currentUser
        if (user != null && user.isEmailVerified) {
            // User is signed in and email is verified, navigate to Netflix activity
            startActivity(Intent(this, netflix::class.java))
            finish() // Finish this activity to prevent going back
        } else {
            // User is not signed in or email is not verified, handle accordingly
            // You can show a message to the user and provide options to resend verification email, etc.
            user?.sendEmailVerification()?.addOnCompleteListener { emailTask ->
                if (emailTask.isSuccessful) {
                    // Email sent successfully, navigate to EmailVerificationActivity
                    startActivity(Intent(this, netflix::class.java))
                    finish() // Finish this activity to prevent going back
                } else {
                    // Email not sent, show an error message
                    showToast("Failed to send verification email. Please try again.")
                }
            }}

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}
