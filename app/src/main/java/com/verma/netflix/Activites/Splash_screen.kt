package com.verma.netflix.Activites

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import com.verma.netflix.R

class Splash_screen : AppCompatActivity() {
    private lateinit var animationView: LottieAnimationView
    private lateinit var mediaPlayer: MediaPlayer

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        animationView = findViewById(R.id.lottie_layer_name)  // Replace with your LottieAnimationView ID
        mediaPlayer = MediaPlayer.create(this, R.raw.ffect)  // Replace with your sound file name without extension


        animationView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                // Start playing the sound when the animation starts
                mediaPlayer.start()
            }

            override fun onAnimationEnd(animation: Animator) {
                // Handle animation end if needed
                val intent = Intent(this@Splash_screen, MainActivity::class.java)
                startActivity(intent)

            }

            override fun onAnimationCancel(animation: Animator) {
                // Handle animation cancel if needed

            }

            override fun onAnimationRepeat(animation: Animator) {
                // Handle animation repeat if needed
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()  // Release MediaPlayer resources when the activity is destroyed
    }

}