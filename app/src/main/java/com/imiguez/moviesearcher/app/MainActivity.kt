package com.imiguez.moviesearcher.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.imiguez.moviesearcher.ui.home.HomeScreen
import com.imiguez.moviesearcher.ui.home.HomeViewModel
import com.imiguez.moviesearcher.ui.movie.MovieScreen
import com.imiguez.moviesearcher.ui.movie.MovieViewModel
import com.imiguez.moviesearcher.ui.themes.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val _homeViewModel by viewModels<HomeViewModel>()
    private val _movieViewModel by viewModels<MovieViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            val navController = rememberNavController()
            AppTheme {
                NavHost (
                    navController = navController,
                    startDestination = "home"
                ) {
                    composable(route = "home") {
                        HomeScreen(
                            homeViewModel = _homeViewModel,
                             onSelectMovie = { id ->
                                 navController.navigate("movie/${id}")
                             }
                        )
                    }
                    composable(route = "movie/{id}") { backStackEntry ->
                        // TODO: add a trycatch block, if id isnt an int should redirect to a 404 screen.
                        val id = backStackEntry.arguments?.getString("id")?.toInt()
                        if (id != null) MovieScreen(id, _movieViewModel)
                    }
                }
            }
        }
    }
}