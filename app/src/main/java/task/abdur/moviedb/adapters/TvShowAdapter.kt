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
import task.abdur.moviedb.activities.TvShowActivity
import task.abdur.moviedb.models.Movie
import task.abdur.moviedb.models.TV
import task.abdur.moviedb.utils.PreferenceManager

class TvShowAdapter(private val context: Context, private var tvs: List<TV>) :
    RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    private val preferenceManager = PreferenceManager(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tv = tvs[position]

        holder.titleTextView.text = tv.name
        holder.releaseDateTextView.text = tv.first_air_date

        // Load the tv poster using Glide or your preferred image loading library
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500/"+ tv.poster_path)
            .into(holder.posterImageView)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, TvShowActivity::class.java)
            intent.putExtra("tv", tv) // tv is an instance of Movie
            context.startActivity(intent)
        }
        // Set the favorite button based on the isFavorite property
        if (preferenceManager.isFavoriteTv(tv.id)) {
            holder.favoriteButton.setImageResource(R.drawable.baseline_favorite_24)
        } else {
            holder.favoriteButton.setImageResource(R.drawable.baseline_favorite_border_24)
        }
        holder.favoriteButton.setOnClickListener {
            onFavoriteClick(tv)
        }

    }

    override fun getItemCount(): Int {
        return tvs.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val posterImageView: ImageView = itemView.findViewById(R.id.imageViewPoster)
        val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
        val releaseDateTextView: TextView = itemView.findViewById(R.id.textViewReleaseDate)
        val favoriteButton: ImageButton = itemView.findViewById(R.id.favoriteButton)
    }
    fun setTvs(tvList: List<TV>) {
        tvs = tvList
        notifyDataSetChanged() // Notify the adapter that the data has changed
    }
     fun onFavoriteClick(tv: TV) {
        // Toggle the favorite state using the FavoriteMovieManager
        val tvId = tv.id
        if (!preferenceManager.isFavoriteTv(tvId)) {
            preferenceManager.addFavoriteTv(tvId)
        } else {
            preferenceManager.removeFavoriteTV(tvId)
        }

        // Notify the adapter that the dataset has changed
        notifyDataSetChanged()
    }
}
