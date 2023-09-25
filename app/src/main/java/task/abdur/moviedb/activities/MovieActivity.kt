package task.abdur.moviedb.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import task.abdur.moviedb.R
import task.abdur.moviedb.databinding.ActivityHomeBinding
import task.abdur.moviedb.databinding.ActivityMovieBinding
import task.abdur.moviedb.models.Movie
import task.abdur.moviedb.ui.movie.MovieViewModel

class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Find TextViews in your layout to display the details
        val movie = intent.getParcelableExtra<Movie>("movie")
        if (movie != null) {
            // Use the movie object
            binding.titleTextView.text = movie.title
            binding.releaseDateTextView.text = "Release Date: ${movie.release_date}"
            binding.overviewTextView.text = movie.overview
            binding.averageVoteTextView.text = "Average Vote: ${movie.vote_average}"
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/"+ movie.poster_path)
                .into(binding.moviePosterImageView)
        }

    }
}