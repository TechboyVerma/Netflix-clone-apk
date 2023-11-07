package com.verma.netflix.Activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.verma.netflix.Activites.EmailVerificationActivity
import com.verma.netflix.R

class signin2 : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin2)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        val email1 = findViewById<TextInputEditText>(R.id.email1)
        val pass1 = findViewById<TextInputEditText>(R.id.pass1)
        val emailContainer: TextInputLayout = findViewById(R.id.emailContainer)
        val passContainer: TextInputLayout = findViewById(R.id.passContainer)

        val signinButton = findViewById<LinearLayout>(R.id.siginbutton)
        val phonenumber = findViewById<TextView>(R.id.ph)

        

        signinButton.setOnClickListener {
            val email = email1.text.toString().trim()
            val password = pass1.text.toString().trim()

            // Reset error messages
            emailContainer.error = null
            passContainer.error = null

            var isValid = true

            // Validate email and password
            if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailContainer.error = "Enter a valid email address"
                isValid = false
            }
            if (password.isEmpty() || password.length < 6 || password.length > 10) {
                passContainer.error = "Password must be between 6 and 10 characters"
                isValid = false
            }

            // If validation fails, return
            if (!isValid) {
                return@setOnClickListener
            }

            // Firebase SignUp Authentication
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        user?.let {
                            // Create a reference to the user node in the database
                            val usersRef = database.reference.child("users").child(it.uid)

                            // Create a HashMap to store user data
                            val userData = HashMap<String, Any>()
                            userData["email"] = email

                            // Set the user data in the database
                            usersRef.setValue(userData)

                            // Sign up success, send verification email
                            user.sendEmailVerification().addOnCompleteListener { emailTask ->
                                if (emailTask.isSuccessful) {
                                    // Email sent, update UI or show a verification screen
                                    // For example, navigate to EmailVerificationActivity
                                    startActivity(Intent(this, EmailVerificationActivity::class.java))
                                    finish() // Finish the current activity to prevent going back
                                } else {
                                    showToast("Failed to send verification email. Please try again.")
                                }
                            }
                        }
                    } else {
                        showToast("Sign up failed. Please try again.")
                    }
                }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
