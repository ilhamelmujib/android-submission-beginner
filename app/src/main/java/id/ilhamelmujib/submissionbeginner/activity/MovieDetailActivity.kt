package id.ilhamelmujib.submissionbeginner.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import id.ilhamelmujib.submissionbeginner.Movie
import id.ilhamelmujib.submissionbeginner.databinding.ActivityMovieDetailBinding

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE = "movie"
    }

    private lateinit var binding: ActivityMovieDetailBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(MOVIE, Movie::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(MOVIE)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = movie?.title

        binding.run {
            tvOverview.text = movie?.overview
            tvRating.text = "Rating ${movie?.voteAverage}"
            Glide
                .with(this@MovieDetailActivity)
                .load("https://image.tmdb.org/t/p/original${movie?.posterPath}")
                .centerCrop()
                .placeholder(ColorDrawable(Color.GRAY))
                .into(ivPoster)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}