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

    data class ErrorState (
        var msg: String,
        var exists: Boolean
    )
    private val _hasLaunched = MutableStateFlow<Boolean>(false)
    val hasLaunched = _hasLaunched.asStateFlow()

    fun setHasLaunched(hasLaunched: Boolean) {
        this._hasLaunched.value = hasLaunched
    }

    private val _loading = MutableStateFlow<Boolean>(true)
    val loading = _loading.asStateFlow()
    private val _error = MutableStateFlow<ErrorState>(ErrorState(
        msg = "",
        exists = false,
    ))
    val error = _error.asStateFlow()
    private val _page = MutableStateFlow<Int>(1)
    val page = _page.asStateFlow()
    private val _movies = MutableStateFlow<MutableList<ListedMovieModel>>(mutableListOf())
    val movies = _movies.asStateFlow()


    fun getPopularMovies() {
        viewModelScope.launch {
            _error.value.msg = ""
            _error.value.exists = false
            _loading.value = true
            try {
                if (_page.value < 1) {
                    _error.value.msg = "The page must be an integer greater than 1."
                    _error.value.exists = true
                    return@launch
                }
                val response = moviesRepo.getPopularMovies(_page.value)
                if (!response.isNullOrEmpty()) _movies.value.addAll(response)
                _loading.value = false
                return@launch
            } catch (e: Exception) {
                _error.value.msg = e.message.toString()
                _error.value.exists = true
                return@launch
            } finally {
                _loading.value = false
            }
        }
    }
}