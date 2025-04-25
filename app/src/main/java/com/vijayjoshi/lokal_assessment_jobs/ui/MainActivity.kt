package com.vijayjoshi.lokal_assessment_jobs.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.vijayjoshi.lokal_assessment_jobs.R
import com.vijayjoshi.lokal_assessment_jobs.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setUpSmoothBottomBar()




    }
    private fun setUpSmoothBottomBar() {
        // Set up Navigation Controller
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController
        // Set up SmoothBottomBar navigation with NavController
        binding.bottomBar.onItemSelected = { index ->
            when (index) {
                0 -> navController.navigate(R.id.jobFragment)
                1 -> navController.navigate(R.id.bookMarkFragment)
            }
        }
    }


}