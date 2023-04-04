package id.ilhamelmujib.submissionbeginner.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.ilhamelmujib.submissionbeginner.adapter.MovieAdapter
import id.ilhamelmujib.submissionbeginner.Movies
import id.ilhamelmujib.submissionbeginner.R
import id.ilhamelmujib.submissionbeginner.databinding.ActivityMovieBinding
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieBinding

    @OptIn(ExperimentalSerializationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val stream = resources.openRawResource(R.raw.movies)
        val json = Json.decodeFromStream<Movies>(stream)

        val movieAdapter = MovieAdapter(json.movies)
        binding.rvMovies.run {
            layoutManager = LinearLayoutManager(this@MovieActivity)
            adapter = movieAdapter
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about -> startActivity(Intent(this, AboutActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}
