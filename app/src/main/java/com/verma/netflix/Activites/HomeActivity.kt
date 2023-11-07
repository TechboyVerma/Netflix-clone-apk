package com.verma.netflix.Activites


import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
//import com.verma.netflix.Adapters.MovieAdapter
import com.verma.netflix.Adapters.SliderAdapter
import com.verma.netflix.Domain.SliderItems

//import com.verma.netflix.Models.MovieViewModel
import com.verma.netflix.R

class HomeActivity : AppCompatActivity() {
    private lateinit var viewPager2: ViewPager2
    private lateinit var slideHandler: Handler

   // private lateinit var viewModel: MovieViewModel
    private lateinit var progressBar: ProgressBar
   // private lateinit var movieAdapter: MovieAdapter // Declare lateinit property


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
       // movieAdapter = MovieAdapter(mutableListOf()) // Initialize the adapter with an empty list or the appropriate data
       // viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        progressBar = findViewById(R.id.progressBar1) // Initialize progressBar using its ID
        viewPager2 = findViewById(R.id.viewPager2)
        slideHandler = Handler(Looper.getMainLooper())
      //  prepareRecyclerView()
        initBanners()
       // observePopularMovies()
    }
/*
    private fun observePopularMovies() {
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        progressBar.visibility = View.VISIBLE // Show progressBar when movies are being loaded
        viewModel.getPopularMovies()
        viewModel.observeMovieLiveData().observe(this, Observer { movieList ->
            if (movieList != null && movieList.isNotEmpty()) {
                progressBar.visibility = View.GONE // Hide progressBar when movies are loaded
                movieAdapter.setMovies(movieList) // Pass the movieList to setMovies method
            } else {
                // Handle the case when movieList is null or empty, for example, show an error message
                // or handle it according to your app's requirements.
            }
        })
    }

 */

    private fun initBanners() {
        val sliderItems = ArrayList<SliderItems>()
        sliderItems.add(SliderItems(R.drawable.wide))
        sliderItems.add(SliderItems(R.drawable.wide1))
        sliderItems.add(SliderItems(R.drawable.wide2))
        sliderItems.add(SliderItems(R.drawable.wide3))
        sliderItems.add(SliderItems(R.drawable.wide4))

        val sliderAdapter = SliderAdapter(sliderItems, viewPager2)
        viewPager2.adapter = sliderAdapter
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.offscreenPageLimit = 5
        viewPager2.overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer(ViewPager2.PageTransformer { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.85f + r * 0.15f
        })
        viewPager2.setPageTransformer(compositePageTransformer)
        viewPager2.setCurrentItem(1, false)

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                slideHandler.removeCallbacksAndMessages(null)
            }
        })

        val slideRunnable = object : Runnable {
            override fun run() {
                viewPager2.currentItem = viewPager2.currentItem + 1
            }

        }

        slideHandler.postDelayed(slideRunnable, 2000)
    }

   /* private fun prepareRecyclerView() {
        // Initialize MovieAdapter
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewBestMovies)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = movieAdapter



        recyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)

        recyclerView.clipChildren = false
        recyclerView.overScrollMode = RecyclerView.OVER_SCROLL_NEVER




    }

    */
}