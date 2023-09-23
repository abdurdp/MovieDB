package task.abdur.moviedb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import task.abdur.moviedb.R
import task.abdur.moviedb.models.Movie

class MovieAdapter(private var movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            // Bind movie data to views in the item layout
            val titleTextView = itemView.findViewById<TextView>(R.id.titleTextView)
            val releaseDateTextView = itemView.findViewById<TextView>(R.id.releaseDateTextView)
            // Bind other views as needed

            titleTextView.text = movie.title
            releaseDateTextView.text = movie.release_date
            // Bind other data as needed
        }
    }
    fun setMovies(movieList: List<Movie>) {
        movies = movieList
        notifyDataSetChanged() // Notify the adapter that the data has changed
    }
}
