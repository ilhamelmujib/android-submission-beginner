package id.ilhamelmujib.submissionbeginner.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.ilhamelmujib.submissionbeginner.Movie
import id.ilhamelmujib.submissionbeginner.activity.MovieDetailActivity
import id.ilhamelmujib.submissionbeginner.databinding.ItemMovieBinding

class MovieAdapter(private val list: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = list[position]
        val context = holder.binding.root.context
        holder.binding.run {
            tvTitle.text = movie.title
            tvOverview.text = movie.overview
            tvRating.text = "Rating ${movie.voteAverage}"
            Glide
                .with(context)
                .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                .centerCrop()
                .placeholder(ColorDrawable(Color.GRAY))
                .into(ivPoster)
        }
        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context, MovieDetailActivity::class.java).apply {
                putExtra(MovieDetailActivity.MOVIE, movie)
            })

        }
    }

    override fun getItemCount(): Int = list.size

    class MovieViewHolder(var binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)


}