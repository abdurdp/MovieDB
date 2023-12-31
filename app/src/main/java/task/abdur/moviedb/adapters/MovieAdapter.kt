package task.abdur.moviedb.adapters


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import task.abdur.moviedb.BuildConfig
import task.abdur.moviedb.R
import task.abdur.moviedb.activities.MovieActivity
import task.abdur.moviedb.models.Movie
import task.abdur.moviedb.utils.PreferenceManager

class MovieAdapter(private val context: Context, private var movies: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val preferenceManager = PreferenceManager(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]

        holder.titleTextView.text = movie.title
        holder.releaseDateTextView.text = movie.release_date

        // Load the movie poster using Glide or your preferred image loading library
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500/"+ movie.poster_path)
            .into(holder.posterImageView)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, MovieActivity::class.java)
            intent.putExtra("movie", movie) // movie is an instance of Movie
            context.startActivity(intent)
        }
        // Set the favorite button based on the isFavorite property
        if (preferenceManager.isFavoriteMovie(movie.id)) {
            holder.favoriteButton.setImageResource(R.drawable.baseline_favorite_24)
        } else {
            holder.favoriteButton.setImageResource(R.drawable.baseline_favorite_border_24)
        }
        holder.favoriteButton.setOnClickListener {
            onFavoriteClick(movie)
        }

    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val posterImageView: ImageView = itemView.findViewById(R.id.imageViewPoster)
        val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
        val releaseDateTextView: TextView = itemView.findViewById(R.id.textViewReleaseDate)
        val favoriteButton: ImageButton = itemView.findViewById(R.id.favoriteButton)
    }
    fun setMovies(movieList: List<Movie>) {
        movies = movieList
        notifyDataSetChanged() // Notify the adapter that the data has changed
    }
     fun onFavoriteClick(movie: Movie) {
        // Toggle the favorite state using the FavoriteMovieManager
        val movieId = movie.id
        if (!preferenceManager.isFavoriteMovie(movieId)) {
            preferenceManager.addFavoriteMovie(movieId)
        } else {
            preferenceManager.removeFavoriteMovie(movieId)
        }

        // Notify the adapter that the dataset has changed
        notifyDataSetChanged()
    }
}
