package com.imiguez.moviesearcher.ui.movie

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imiguez.moviesearcher.R
import com.imiguez.moviesearcher.ddl.model.MovieDetailsModel
import com.imiguez.moviesearcher.ddl.repositories.MovieRespository
import com.imiguez.moviesearcher.ui.common.utils.ErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val moviesRepo: MovieRespository
): ViewModel() {

    private val _loading = MutableStateFlow<Boolean>(true)
    val loading = _loading.asStateFlow()
    private val _error = MutableStateFlow<ErrorState>(ErrorState(
            msg = "",
            exists = false,
            lastAction = {}
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
            _error.value.lastAction = { getMovieDetails(id) }
            _loading.value = true
            try {
                val response = moviesRepo.getMovieDetails(id)
                if (response != null) {
                    _movie.value = response
                    _error.value.exists = false
                }
            } catch (e: Exception) {
                _error.value.msg = context.getString(R.string.a_problem_has_occurred)
                _error.value.exists = true
            } finally {
                _loading.value = false
            }
        }
    }
}