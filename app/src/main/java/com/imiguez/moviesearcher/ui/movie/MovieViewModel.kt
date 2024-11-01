package com.imiguez.moviesearcher.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imiguez.moviesearcher.ddl.model.MovieDetailsModel
import com.imiguez.moviesearcher.ddl.repositories.MovieRespository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val moviesRepo: MovieRespository
): ViewModel() {

    data class ErrorState (
        var msg: String,
        var exists: Boolean
    )

    private val _loading = MutableStateFlow<Boolean>(true)
    val loading = _loading.asStateFlow()
    private val _error = MutableStateFlow<ErrorState>(ErrorState(
            msg = "",
            exists = false,
        )
    )

    private val _hasLaunched = MutableStateFlow<Boolean>(false)
    val hasLaunched = _hasLaunched.asStateFlow()

    fun setHasLaunched(hasLaunched: Boolean) {
        this._hasLaunched.value = hasLaunched
    }

    val error = _error.asStateFlow()
    private val _movie = MutableStateFlow<MovieDetailsModel?>(null)
    val movie = _movie.asStateFlow()

    fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            _error.value.msg = ""
            _error.value.exists = false
            _loading.value = true
            try {
                val response = moviesRepo.getMovieDetails(id)
                if (response != null) _movie.value = response
            } catch (e: Exception) {
                _error.value.msg = e.message.toString()
                _error.value.exists = true
            } finally {
                _loading.value = false
            }
        }
    }
}