package task.abdur.moviedb.ui.movie

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import task.abdur.moviedb.models.Movie
import task.abdur.moviedb.network.MovieApiService

class MovieViewModel(application: Application)  : AndroidViewModel(application ) {
    private val apiService = MovieApiService.create()

    private val _trendingMovies = MutableLiveData<List<Movie>>()
    val trendingMovies: LiveData<List<Movie>> get() = _trendingMovies

     fun  fetchTrendingMovies() {
        viewModelScope.launch {
            try {
                val response = apiService.getTrendingMovies()
                if (response.isSuccessful) {
                    val movieResponse = response.body()
                    val movies = movieResponse?.results ?: emptyList()
                    _trendingMovies.postValue(movies)
                } else {
                    // Handle error
                }
            } catch (e: Exception) {
                print(e.message)
                // Handle exceptions (e.g., network errors) here
            }
        }

    }
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}

