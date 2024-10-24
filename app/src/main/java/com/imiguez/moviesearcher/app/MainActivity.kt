package com.imiguez.moviesearcher.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.imiguez.moviesearcher.ui.home.HomeScreen
import com.imiguez.moviesearcher.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val _homeViewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            HomeScreen(
                homeViewModel = _homeViewModel
            )
        }
    }
}