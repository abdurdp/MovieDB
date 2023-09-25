package task.abdur.moviedb.repository
import android.content.Context
import task.abdur.moviedb.models.TVResponse
import task.abdur.moviedb.network.ApiService
import task.abdur.moviedb.network.TvApi
import task.abdur.moviedb.utils.NetworkUtils
import task.abdur.moviedb.utils.PreferenceManager

class TVRepository(private val tvApiService: TvApi, private val context: Context) {
    private val preferenceManager = PreferenceManager(context)
    suspend fun getTrendingTv(): Result<TVResponse> {

        return if (NetworkUtils.isNetworkConnected(context)) {
            // Network is available, make the API call
            try {
                val response = tvApiService.getTrendingTvs()
                if (response.isSuccessful) {
                    val tvResponse = response.body()
                    if (tvResponse != null) {
                        // Cache the fetched data here if needed
                        preferenceManager.cacheTvData(tvResponse)
                        return Result.success(tvResponse)
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
            val cachedData = preferenceManager.getCachedTvData()
            return if (cachedData != null) {
                Result.success(cachedData)
            } else {
                Result.failure(Throwable(("No network connection, and no cached data available")))
            }
        }
    }

}
