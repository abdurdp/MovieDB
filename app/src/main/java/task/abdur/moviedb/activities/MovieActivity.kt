package task.abdur.moviedb.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import task.abdur.moviedb.R
import task.abdur.moviedb.ui.movie.MovieViewModel

class MovieActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

    }
}