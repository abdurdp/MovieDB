package task.abdur.moviedb.ui.tv

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import task.abdur.moviedb.models.TV
import task.abdur.moviedb.network.ApiService
import task.abdur.moviedb.repository.TVRepository

class TvShowViewModel (application: Application) : AndroidViewModel(application){
    private val apiService = ApiService.createTvApi()
    private val tvRepository = TVRepository(apiService, application)

    private val _trendingTvs = MutableLiveData<List<TV>>()
    val trendingTvs: LiveData<List<TV>> get() = _trendingTvs

    fun fetchTrendingTvs() {
        viewModelScope.launch {
            try {
                val response = tvRepository.getTrendingTv()

                response.onSuccess {
                    val tvs = it.results
                    _trendingTvs.postValue(tvs)
                }
                response.onFailure {
                    _trendingTvs.postValue(emptyList())
                }
            } catch (e: Exception) {
                print(e.message)
                // Handle exceptions (e.g., network errors) here
            }
        }

    }
    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
}