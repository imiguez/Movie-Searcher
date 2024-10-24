package com.imiguez.moviesearcher.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imiguez.moviesearcher.ddl.model.ListedMovieModel
import com.imiguez.moviesearcher.ddl.repositories.MovieRespository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesRepo: MovieRespository
): ViewModel() {
    private val _loading = MutableStateFlow<Boolean>(true)
    val loading = _loading.asStateFlow()
    private val _error = MutableStateFlow<String>("")
    val error = _error.asStateFlow()
    private val _page = MutableStateFlow<Int>(1)
    val page = _page.asStateFlow()
    private val _movies = MutableStateFlow<MutableList<ListedMovieModel>>(mutableListOf())
    val movies = _movies.asStateFlow()


    suspend fun loadMoviesToDiscover() {
        viewModelScope.launch {
            if (_page.value < 1) {
                _error.value = "The page must be an integer greater than."
                return@launch
            }
            _loading.value = true
            val response = moviesRepo.getMoviesToDiscover(_page.value)
            // TODO: after customize the error exceptions and error handling on the ddl layer, refactor this error handling.
            if (response == null) {
                _error.value = "The response returned null."
                return@launch
            }
            _movies.value.addAll(response)
            _loading.value = false
            return@launch
        }
    }
}