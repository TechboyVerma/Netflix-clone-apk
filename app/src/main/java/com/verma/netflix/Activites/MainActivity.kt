package com.verma.netflix.Activites

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ActionTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemChangeListener
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.interfaces.TouchListener
import com.denzcoskun.imageslider.models.SlideModel
import com.verma.netflix.Activites.sign_in
import com.verma.netflix.Activites.sign_up
import com.verma.netflix.R


class MainActivity : AppCompatActivity() {
    val imageList = ArrayList<SlideModel>() // Create image list





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Add images to the imageList using SlideModel
        imageList.add(SlideModel(R.drawable.st, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.chil, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.me, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.end, ScaleTypes.FIT))


        val imageSlider = findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(imageList)
        // Initialize the PopupMenu
        val menu_button = findViewById<ImageView>(R.id.menu_button)

        val popupMenu = PopupMenu(this, menu_button)
        popupMenu.menuInflater.inflate(R.menu.menu_main, popupMenu.menu)

        // Set item click listener for the PopupMenu
        popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem): Boolean {
                // Handle menu item clicks here
                when (item.itemId) {
                    R.id.menu_item1 -> {
                        // Handle menu item 1 click
                        return true
                    }

                    R.id.menu_item2 -> {
                        // Handle menu item 2 click
                        return true
                    }
                    // Add more menu items as needed
                    else -> return false
                }
            }
        })

        // Set click listener for the Menu button
        menu_button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                // Show the PopupMenu when the Menu button is clicked
                popupMenu.show()
            }
        })
        val privacy = findViewById<TextView>(R.id.text1)
        // Open the URL in a web browser when the button is clicked
        privacy.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                // Open the URL in a web browser when the button is clicked
                val privacyUrl = "https://helpful-madeleine-464c66.netlify.app/"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(privacyUrl))
                startActivity(intent)
            }
        })
        val signin = findViewById<TextView>(R.id.text2)
        // Set OnClickListener for the signText TextView
        signin.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                // Start SignInActivity when the TextView is clicked
                val intent = Intent(this@MainActivity, sign_in::class.java)
                startActivity(intent)
            }
        })

        signin.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                // Start SignInActivity when the TextView is clicked
                val intent = Intent(this@MainActivity, sign_in::class.java)
                startActivity(intent)
            }
        })
        val get = findViewById<LinearLayout>(R.id.get)
        get.setOnClickListener {
            // Start SignUpActivity when the button is clicked
            var intent = Intent(this@MainActivity, sign_up::class.java)
            startActivity(intent)
        }
        val getLinearLayout = findViewById<LinearLayout>(R.id.get)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val textView = findViewById<TextView>(R.id.textView)

        getLinearLayout.setOnClickListener {
            // Hide the TextView and show the ProgressBar
            textView.visibility = View.GONE
            progressBar.visibility = View.VISIBLE

            // Simulate some loading process with a delay (for example, 2 seconds)
            Handler().postDelayed({
                // After the loading process is done, show the TextView and hide the ProgressBar
                progressBar.visibility = View.GONE
                textView.visibility = View.VISIBLE
                var intent = Intent(this@MainActivity, sign_up::class.java)
                startActivity(intent)
            }, 1000) // 1000 milliseconds = 1 seconds
        }
    }}