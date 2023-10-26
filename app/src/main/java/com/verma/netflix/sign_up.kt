package com.verma.netflix

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat


class sign_up : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val cancel = findViewById<ImageView>(R.id.cancel_im1)
        val emailText = findViewById<TextInputEditText>(R.id.email1)
        val emailContainer: TextInputLayout = findViewById(R.id.emailContainer)
        val sign: LinearLayout = findViewById(R.id.button111)

        val colorValid = ContextCompat.getColor(this, R.color.boxStrokeColorValid) // Define color for valid input
        val colorInvalid = ContextCompat.getColor(this, R.color.boxStrokeColorInvalid) // Define color for invalid input

        sign.setOnClickListener {

            var email = emailText.text.toString().trim()

            // Reset error state and color
            emailContainer.error = null
            emailContainer.isErrorEnabled = false
            emailContainer.setBoxStrokeColorStateList(ColorStateList.valueOf(colorValid)) // Set default color

            // Validate email
            if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailContainer.error = "Enter a valid email address"
                emailContainer.isErrorEnabled = true // Enable error state for emailContainer
                emailContainer.setBoxStrokeColorStateList(ColorStateList.valueOf(colorInvalid)) // Set invalid color
            }

            // If validation fails, return
            if (emailContainer.isErrorEnabled) {
                return@setOnClickListener
            }
            val progressBar = findViewById<ProgressBar>(R.id.progressBar)
            val textView = findViewById<TextView>(R.id.textView)
            textView.visibility = View.GONE
            progressBar.visibility = View.VISIBLE

            // Simulate some loading process with a delay (for example, 2 seconds)
            Handler().postDelayed({
                // After the loading process is done, show the TextView and hide the ProgressBar
                progressBar.visibility = View.GONE
                textView.visibility = View.VISIBLE
                val intent = Intent(this, signin2::class.java)
                intent.putExtra("EMAIL", email)  // Add email value to the Intent as a String
                startActivity(intent)
            }, 1000)






        }

        cancel.setOnClickListener { // Start SignInActivity when the TextView is clicked
            val intent = Intent(this@sign_up, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
