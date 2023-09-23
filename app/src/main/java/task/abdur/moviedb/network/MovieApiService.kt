package task.abdur.moviedb.network
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieApiService {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    fun create(): MovieApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory()) // Coroutine support
            .build()

        return retrofit.create(MovieApi::class.java)
    }
}
