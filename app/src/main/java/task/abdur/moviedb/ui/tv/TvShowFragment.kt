package task.abdur.moviedb.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import task.abdur.moviedb.adapters.TvShowAdapter
import task.abdur.moviedb.databinding.FragmentTvShowBinding

class TvShowFragment : Fragment() {

    private lateinit var tvShowAdapter: TvShowAdapter
    private var _binding: FragmentTvShowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel =
            ViewModelProvider(this)[TvShowViewModel::class.java]

        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        val root: View = binding.root
        tvShowAdapter = TvShowAdapter(requireContext(),emptyList()) // Initially, an empty list
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = tvShowAdapter
        }
        // Set the adapter to the RecyclerView
        binding.recyclerView.adapter = tvShowAdapter

        // Observe LiveData from the ViewModel and update the adapter when data changes
        viewModel.trendingTvs.observe(requireActivity()) { tvs ->
            tvShowAdapter.setTvs(tvs)
        }
        // Call the function to fetch trending movies
        viewModel.fetchTrendingTvs()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}