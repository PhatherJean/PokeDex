package com.phatherjay.pokedex.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.phatherjay.pokedex.R
import com.phatherjay.pokedex.databinding.ActivityMainBinding
import com.phatherjay.pokedex.utils.ImageUrls
import com.phatherjay.pokedex.utils.loadWithGlide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val navHostFragment by lazy { supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.tvPokeTitle.loadWithGlide(ImageUrls.banner)
        binding.bottomNavigation.setupWithNavController(navHostFragment.navController)
    }
}