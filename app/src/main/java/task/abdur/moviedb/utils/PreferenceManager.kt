package task.abdur.moviedb.utils

import android.content.Context
import com.google.gson.Gson
import task.abdur.moviedb.models.MovieResponse
import task.abdur.moviedb.models.TVResponse

class PreferenceManager(private val context: Context)  {

    private val sharedPreferences =
        context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

    // Saving a movie as a favorite
    fun addFavoriteMovie(movieId: Int) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("movie_$movieId", true)
        editor.apply()
    }

    // Checking if a movie is a favorite
    fun isFavoriteMovie(movieId: Int): Boolean {
        return sharedPreferences.getBoolean("movie_$movieId", false)
    }
    // Removing a movie from favorites
    fun removeFavoriteMovie(movieId: Int) {
        val sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("movie_$movieId") // Remove the movie entry
        editor.apply()
    } // Saving a movie as a favorite
    fun addFavoriteTv(tvId: Int) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("tv_$tvId", true)
        editor.apply()
    }

    // Checking if a movie is a favorite
    fun isFavoriteTv(tvId: Int): Boolean {
        return sharedPreferences.getBoolean("tv_$tvId", false)
    }
    // Removing a movie from favorites
    fun removeFavoriteTV(tvId: Int) {
        val sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("tv_$tvId") // Remove the movie entry
        editor.apply()
    }
     fun getCachedMovieData(): MovieResponse? {
        val cachedDataJson = sharedPreferences.getString("trending_movies", null)
        return if (cachedDataJson != null) {
            // Parse the cached JSON data to MovieResponse
            val gson = Gson()
            gson.fromJson(cachedDataJson, MovieResponse::class.java)
        } else {
            null
        }
    }
     fun getCachedTvData(): TVResponse? {
        val cachedDataJson = sharedPreferences.getString("trending_tvs", null)
        return if (cachedDataJson != null) {
            // Parse the cached JSON data to TVResponse
            val gson = Gson()
            gson.fromJson(cachedDataJson, TVResponse::class.java)
        } else {
            null
        }
    }

     fun cacheMovieData(movieResponse: MovieResponse) {
        val editor = sharedPreferences.edit()

        // Convert movieResponse to JSON and store it in SharedPreferences
        val gson = Gson()
        val movieResponseJson = gson.toJson(movieResponse)

        editor.putString("trending_movies", movieResponseJson)
        editor.apply()
    }
     fun cacheTvData(tvResponse: TVResponse) {
        val editor = sharedPreferences.edit()

        // Convert movieResponse to JSON and store it in SharedPreferences
        val gson = Gson()
        val tvResponseJson = gson.toJson(tvResponse)

        editor.putString("trending_tvs", tvResponseJson)
        editor.apply()
    }
}