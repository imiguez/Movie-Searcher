package com.imiguez.moviesearcher.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imiguez.moviesearcher.ddl.model.ListedMovieModel
import com.imiguez.moviesearcher.ddl.repositories.MovieRespository
import com.imiguez.moviesearcher.ui.common.utils.ErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesRepo: MovieRespository
): ViewModel() {

    private val _hasLaunched = MutableStateFlow<Boolean>(false)
    val hasLaunched = _hasLaunched.asStateFlow()

    fun setHasLaunched(hasLaunched: Boolean) {
        this._hasLaunched.value = hasLaunched
    }

    private val _query = MutableStateFlow<String>("")
    val query = _query.asStateFlow()

    fun setQuery(newQuery: String) {
        _query.value = newQuery
    }

    private val _loading = MutableStateFlow<Boolean>(true)
    val loading = _loading.asStateFlow()
    private val _error = MutableStateFlow<ErrorState>(ErrorState(
        msg = "",
        exists = false,
        lastAction = { getPopularMovies() }
    ))
    val error = _error.asStateFlow()
    private val _page = MutableStateFlow<Int>(1)
    val page = _page.asStateFlow()
    fun setPage(page: Int) { _page.value = page }

    private val _movies = MutableStateFlow<MutableList<ListedMovieModel>>(mutableListOf())
    val movies = _movies.asStateFlow()

    fun preRequest() {
        _error.value = ErrorState(
            msg = "",
            exists = false,
            lastAction = _error.value.lastAction
        )
        _loading.value = true
    }

    fun getPopularMovies(calledFromSearch: Boolean = false) {
        viewModelScope.launch {
            preRequest()
            try {
                if (_page.value < 1) {
                    _error.value.msg = "The page must be an integer greater than 1."
                    _error.value.exists = true
                } else {
                    val response = moviesRepo.getPopularMovies(_page.value)
                    if (!response.isNullOrEmpty()) _movies.value.addAll(response)
                }
            } catch (e: Exception) {
                _error.value = ErrorState(
                    msg = e.message.toString(),
                    exists = true,
                    lastAction = { if (calledFromSearch) searchMovieByText() else getPopularMovies() }
                )
            } finally {
                _loading.value = false
            }
        }
    }

    fun searchMovieByText() {
        _movies.value = mutableListOf()
        if (_query.value.isNullOrEmpty()) {
            getPopularMovies()
            return
        }
        preRequest()
        viewModelScope.launch {
            try {
                if (_page.value < 1) {
                    _error.value.msg = "The page must be an integer greater than 1."
                    _error.value.exists = true
                } else {
                    val response = moviesRepo.searchMovieByText(_query.value, _page.value)
                    if (!response.isNullOrEmpty()) _movies.value.addAll(response)
                }
            } catch (e: Exception) {
                _error.value = ErrorState(
                    msg = e.message.toString(),
                    exists = true,
                    lastAction = { searchMovieByText() }
                )
            } finally {
                _loading.value = false
            }
        }
    }
}