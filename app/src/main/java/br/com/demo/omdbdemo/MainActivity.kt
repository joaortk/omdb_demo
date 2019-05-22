package br.com.demo.omdbdemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import br.com.demo.omdbdemo.domain.model.Movie
import br.com.demo.omdbdemo.feature.detail.view.MovieDetailActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var c: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myToolbar = findViewById<Toolbar>(R.id.main_toolbar)
        setSupportActionBar(myToolbar)
        OmdbDemoApplication.appComponent.inject(this)

        startActivity(Intent(this, MovieDetailActivity::class.java).apply {
            putExtra(MovieDetailActivity.MOVIE_ARG, Movie(title = "Avengers: Endgame", imdbId = "tt4154796"))
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        (menu?.findItem(R.id.action_search)?.actionView as? SearchView)?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("SearchView", "OnSubmit $query")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("SearchView", "OnChange $newText")
                return true
            }
        })
        return true
    }
}
