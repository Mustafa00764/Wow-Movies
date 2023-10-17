package com.example.wowmovie_.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.wowmovie_.R
import com.example.wowmovie_.data.remote.SelectedListener
import com.example.wowmovie_.ui.fragment.MoviesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), SelectedListener {
lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }


    private fun initViews() {
        val ncNavHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHost

        navController=ncNavHost.navController
        navController

        val navHostFradgment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFradgment.navController
        val bNavMenu = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bNavMenu.setupWithNavController(navController)
        bNavMenu.setOnClickListener {

        }
    }


   override fun onSelected(id: Int) {
//        val bundle = Bundle()
//        bundle.putInt("id", id)
//
//        val detailsFragment = MoviesFragment()
//        detailsFragment.arguments = bundle
//
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.nav_host_fragment, detailsFragment)
//            .addToBackStack(null)
//            .commit()
    }



}


