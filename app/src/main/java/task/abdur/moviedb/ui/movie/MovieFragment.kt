package task.abdur.moviedb.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import task.abdur.moviedb.adapters.MovieAdapter
import task.abdur.moviedb.databinding.FragmentMovieBinding

class MovieFragment : Fragment() {

    private lateinit var movieAdapter: MovieAdapter
    private var _binding: FragmentMovieBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel =
            ViewModelProvider(this)[MovieViewModel::class.java]

        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        val root: View = binding.root
        movieAdapter = MovieAdapter(emptyList()) // Initially, an empty list

        // Set the adapter to the RecyclerView
        binding.recyclerView.adapter = movieAdapter

        // Observe LiveData from the ViewModel and update the adapter when data changes
        viewModel.trendingMovies.observe(requireActivity()) { movies ->
            movieAdapter.setMovies(movies)
        }

        // Call the function to fetch trending movies
        viewModel.fetchTrendingMovies()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}