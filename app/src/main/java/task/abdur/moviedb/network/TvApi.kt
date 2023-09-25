package task.abdur.moviedb.network

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import task.abdur.moviedb.BuildConfig
import task.abdur.moviedb.models.MovieResponse
import task.abdur.moviedb.models.TVResponse

interface TvApi {
    // Define your API endpoints here

    @GET("trending/tv/day?language=en-US&api_key=c33832f707ec95387239c7014b8fb76b")
    suspend fun getTrendingTvs(): Response<TVResponse>
}
