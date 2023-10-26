package com.verma.netflix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.ProgressBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.verma.netflix.frangments.TV
import com.verma.netflix.frangments.anime
import com.verma.netflix.frangments.download
import com.verma.netflix.frangments.home
import com.verma.netflix.frangments.sports


class netflix : AppCompatActivity() {
    // Inside your activity or fragment class
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_netflix)
        // Add images to the imageList using SlideModel
        auth = FirebaseAuth.getInstance()



        val getLinearLayout = findViewById<LinearLayout>(R.id.search_layout)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val editText = findViewById<EditText>(R.id.search_edittext)
        val imageView=findViewById<ImageView>(R.id.search_icon)

        editText.setOnClickListener(){

            val intent = Intent(this,searching::class.java)
            startActivity(intent)
        }
        getLinearLayout.setOnClickListener(){
            val intent = Intent(this,searching::class.java)
            startActivity(intent)
        }
        imageView.setOnClickListener(){
            val intent = Intent(this,searching::class.java)
            startActivity(intent)
        }


        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation_view)
        val homeMenuItem = bottomNavigationView.menu.findItem(R.id.menu_item1)
        homeMenuItem.isEnabled = true
        homeMenuItem.isChecked = true

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            when (menuItem.itemId) {
                R.id.menu_item1 -> {
                    // Handle Home item click
                    // Replace HomeFragment with the appropriate fragment class for the Home screen
                    val homeFragment = home()
                    fragmentTransaction.replace(R.id.container, homeFragment)
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()
                    true
                }
                R.id.menu_item2 -> {
                    // Handle TV item click
                    val tvFragment = TV()
                    fragmentTransaction.replace(R.id.container, tvFragment)
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()
                    true
                }
                R.id.menu_item3 -> {
                    // Handle Anime item click
                    val animeFragment = anime()
                    fragmentTransaction.replace(R.id.container, animeFragment)
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()
                    true
                }
                R.id.menu_item4 -> {
                    // Handle Sports item click
                    val sportsFragment = sports()
                    fragmentTransaction.replace(R.id.container, sportsFragment)
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()
                    true
                }
                R.id.menu_item5 -> {
                    // Handle Download item click
                    val downloadFragment = download()
                    fragmentTransaction.replace(R.id.container, downloadFragment)
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()
                    true
                }
                else -> false
            }
        }
        val profile=findViewById<ImageView>(R.id.profile)
        val popupMenu = PopupMenu(this, profile)
        popupMenu.menuInflater.inflate(R.menu.profile, popupMenu.menu)

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
                        signOut() // Call your sign out method here
                        true
                        return true
                    }
                    // Add more menu items as needed
                    else -> return false
                }
            }
        })

        // Set click listener for the Menu button
        profile.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                // Show the PopupMenu when the Menu button is clicked
                popupMenu.show()
            }
        })

    }

    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
        // Redirect the user to the sign-in screen or perform other necessary actions
        // For example, you can start a new activity:
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        // finish() // Optionally, finish the current activity to prevent going back to it
    }

}