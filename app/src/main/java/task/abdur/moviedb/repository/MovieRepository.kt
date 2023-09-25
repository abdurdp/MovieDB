package task.abdur.moviedb.repository
import android.content.Context
import task.abdur.moviedb.models.MovieResponse
import task.abdur.moviedb.network.MovieApi
import task.abdur.moviedb.utils.NetworkUtils
import task.abdur.moviedb.utils.PreferenceManager

class MovieRepository(private val movieApiService: MovieApi, private val context: Context) {
    private val preferenceManager = PreferenceManager(context)
    suspend fun getTrendingMovies(): Result<MovieResponse> {

        return if (NetworkUtils.isNetworkConnected(context)) {
            // Network is available, make the API call
            try {
                val response = movieApiService.getTrendingMovies()
                if (response.isSuccessful) {
                    val movieResponse = response.body()
                    if (movieResponse != null) {
                        // Cache the fetched data here if needed
                        preferenceManager.cacheMovieData(movieResponse)
                        return Result.success(movieResponse)
                    }
                }
                // Handle API call failure here
                Result.failure(Throwable("Failed to fetch data from the server"))
            } catch (e: Exception) {
                // Handle exceptions if any
                Result.failure(Throwable("An error occurred: ${e.message}"))
            }
        } else {

            // No network connection, handle accordingly (e.g., return cached data)
            val cachedData = preferenceManager.getCachedMovieData()
            return if (cachedData != null) {
                Result.success(cachedData)
            } else {
                Result.failure(Throwable(("No network connection, and no cached data available")))
            }
        }
    }

}
