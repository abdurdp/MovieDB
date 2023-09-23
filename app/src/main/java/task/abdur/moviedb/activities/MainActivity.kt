package task.abdur.moviedb.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import task.abdur.moviedb.databinding.ActivityMainBinding
import task.abdur.moviedb.ui.movie.MovieViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners() {
        binding.btnMovie.setOnClickListener {
            // Start the MovieActivity
            val intent = Intent(this, MovieActivity::class.java)
            startActivity(intent)
        }

        binding.btnTvShow.setOnClickListener {
            // Start the TvShowActivity
            val intent = Intent(this, TvShowActivity::class.java)
            startActivity(intent)
        }

        binding.btnGraph.setOnClickListener {
            // Start the GraphActivity
            val intent = Intent(this, GraphActivity::class.java)
            startActivity(intent)
        }
    }
}