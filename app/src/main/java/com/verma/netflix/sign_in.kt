package com.verma.netflix

import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View

import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

import androidx.core.content.ContextCompat

import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth



import kotlin.math.sign

class sign_in : AppCompatActivity() {

    // Initialize Firebase Auth
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        val learnTextView: TextView = findViewById(R.id.learn)
        val expandedTextView: TextView = findViewById(R.id.expandedText)
        val signBackTextView: TextView = findViewById(R.id.signback)
        val help = findViewById<TextView>(R.id.help)
        val back = findViewById<ImageView>(R.id.back)


        auth = Firebase.auth
        val emailText = findViewById<TextInputEditText>(R.id.email)
        val emailContainer: TextInputLayout = findViewById(R.id.emailContainer)
        val passwordText = findViewById<TextInputEditText>(R.id.pass)
        val passContainer: TextInputLayout = findViewById(R.id.passContainer)
        var login: LinearLayout = findViewById(R.id.siginbutton)

        login.setOnClickListener {
            var email = emailText.text.toString()
            var password = passwordText.text.toString()
            // Set the error text color for emailContainer
            emailContainer.setErrorTextColor(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.halo_or)))
            // Set the error text color for passContainer
            passContainer.setErrorTextColor(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.halo_or)))


            emailContainer.error = null
            passContainer.error = null

            var isValid = true

            // Validate email
            if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailContainer.error = "Enter a valid email address"
                isValid = false
            }

            // Validate password
            if (password.isEmpty()) {
                passContainer.error = "Enter a password"
                isValid = false
            } else if (password.length < 6) {
                passContainer.error = "Password must be at least 6 characters"
                isValid = false
            } else if (password.length > 10) {
                passContainer.error = "Password must be at most 10 characters"
                isValid = false
            }

            // If validation fails, return
            if (!isValid) {
                return@setOnClickListener
            }

            if (!email.isEmpty() && !password.isEmpty()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

                            val progressBar = findViewById<ProgressBar>(R.id.progressBar)
                            val textView = findViewById<TextView>(R.id.textView)
                            textView.visibility = View.GONE
                            progressBar.visibility = View.VISIBLE

                            // Simulate some loading process with a delay (for example, 2 seconds)
                            Handler().postDelayed({
                                // After the loading process is done, show the TextView and hide the ProgressBar
                                progressBar.visibility = View.GONE
                                textView.visibility = View.VISIBLE
                                startActivity(Intent(this, netflix::class.java))
                            }, 1000)
                        } else {
                            val progressBar = findViewById<ProgressBar>(R.id.progressBar)
                            val textView = findViewById<TextView>(R.id.textView)
                            textView.visibility = View.GONE
                            progressBar.visibility = View.VISIBLE

                            // Simulate some loading process with a delay (for example, 2 seconds)
                            Handler().postDelayed({
                                // After the loading process is done, show the TextView and hide the ProgressBar
                                progressBar.visibility = View.GONE
                                textView.visibility = View.VISIBLE

                            }, 1000)
                            if (password.isEmpty()) {
                                passContainer.error = "Enter a password"
                                isValid = true
                            }

                            // If validation fails, return

                            Toast.makeText(
                                baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                // Validate email
                if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailContainer.error = "Enter a valid email address"
                    isValid = false
                }

                // Validate password
                if (password.isEmpty()) {
                    passContainer.error = "Enter a password"
                    isValid = false
                } else if (password.length < 6) {
                    passContainer.error = "Password must be at least 6 characters"
                    isValid = false
                } else if (password.length > 10) {
                    passContainer.error = "Password must be at most 10 characters"
                    isValid = false
                }

                // If validation fails, return
                if (!isValid) {
                    return@setOnClickListener
                }

            }
        }


        // Set click listener for the "Learn more" TextView
        learnTextView.setOnClickListener {
            // Toggle visibility of the expanded TextView
            if (expandedTextView.visibility == View.VISIBLE) {
                expandedTextView.visibility = View.GONE
            } else {
                expandedTextView.visibility = View.VISIBLE
            }
        }
        signBackTextView.setOnClickListener {
            // Start MainActivity when signback TextView is clicked
            val intent = Intent(this@sign_in, MainActivity::class.java)
            startActivity(intent)
        }
        back.setOnClickListener {
            // Start MainActivity when signback TextView is clicked
            val intent = Intent(this@sign_in, MainActivity::class.java)
            startActivity(intent)
        }
        // Open the URL in a web browser when the button is clicked
        help.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                // Open the URL in a web browser when the button is clicked
                val privacyUrl = "https://helpful-madeleine-464c66.netlify.app/"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(privacyUrl))
                startActivity(intent)
            }
        })
        //for current user
        fun onStart() {
            super.onStart()
            val currentUser = auth.currentUser
            if (currentUser != null) {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }}