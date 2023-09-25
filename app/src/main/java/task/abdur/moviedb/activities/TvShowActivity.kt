package task.abdur.moviedb.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import task.abdur.moviedb.R
import task.abdur.moviedb.databinding.ActivityMovieBinding
import task.abdur.moviedb.databinding.ActivityTvShowBinding
import task.abdur.moviedb.models.Movie
import task.abdur.moviedb.models.TV

class TvShowActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTvShowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Find TextViews in your layout to display the details
        val tv = intent.getParcelableExtra<TV>("tv")
        if (tv != null) {
            // Use the movie object
            binding.titleTextView.text = tv.name
            binding.releaseDateTextView.text = "First Aired Date: ${tv.first_air_date}"
            binding.overviewTextView.text = tv.overview
            binding.averageVoteTextView.text = "Average Vote: ${tv.vote_average}"
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/"+ tv.poster_path)
                .into(binding.tvPosterImageView)
        }
    }
}