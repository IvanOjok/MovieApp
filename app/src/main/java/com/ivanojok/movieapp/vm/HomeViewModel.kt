package com.ivanojok.movieapp.vm

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ivanojok.movieapp.data.MovieResponse
import com.ivanojok.movieapp.data.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    val currentName: MutableLiveData<Response> by lazy {
        MutableLiveData<Response>(Response.Loading)
    }
    val data = currentName as LiveData<Response>

    init {
        observeData()
    }

    fun observeData() {
        viewModelScope.async {
           getMovies().collect {
                when(it) {
                    is HomeViewModel.Response.Loading -> {
                        currentName.value = Response.Loading
                    }
                    is HomeViewModel.Response.Failure -> {
                        //show a dialog fragment
                        currentName.value = Response.Failure(it.error)
                    }
                    is HomeViewModel.Response.Success -> {
                        currentName.value = Response.Success(it.movies)

                    }
                }
            }
        }


    }


    suspend fun getMovies(): Flow<Response> = flow {
        emit(Response.Loading)
        try {
            val job = viewModelScope.async(Dispatchers.IO) {
                val request = RetrofitInstance().getService().getPopularMovies("56673377785bb28521467dd806b882b2")
                request
            }
             val movies = job.await()
            emit(Response.Success(movies))
        } catch (t: Throwable) {
            emit(Response.Failure(t.message?:""))
        }

    }


    sealed class Response {
        object Loading: Response()
        class Success(val movies:MovieResponse): Response()
        class Failure(val error:String): Response()
    }

}