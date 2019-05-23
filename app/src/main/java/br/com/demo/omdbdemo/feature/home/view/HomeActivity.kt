package br.com.demo.omdbdemo.feature.home.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import br.com.demo.omdbdemo.OmdbDemoApplication
import br.com.demo.omdbdemo.R
import br.com.demo.omdbdemo.databinding.ActivityHomeBinding
import br.com.demo.omdbdemo.di.ViewModelFactory
import br.com.demo.omdbdemo.domain.model.Movie
import br.com.demo.omdbdemo.feature.detail.view.MovieDetailActivity
import br.com.demo.omdbdemo.feature.home.viewmodel.HomeViewModel
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: HomeViewModel
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        setupBinding()
        setupViewModel()
        setSupportActionBar(binding.mainToolbar)
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.viewModel = viewModel
        binding.moviesRecyclerview.layoutManager = GridLayoutManager(this, 3)
        binding.moviesRecyclerview.adapter = MoviesAdapter()

    }

    private fun setupViewModel() {
        viewModel.liveDataMediator.observe(this, Observer { movies ->
            (binding.moviesRecyclerview.adapter as? MoviesAdapter)?.let {
                it.items = movies
                it.listener = object : MoviesAdapterListener {
                    override fun didSelectMovie(movie: Movie) {
                        startActivity(Intent(this@HomeActivity, MovieDetailActivity::class.java).apply {
                            putExtra(MovieDetailActivity.MOVIE_ARG, movie)
                        })
                    }
                }
            }

        })
    }

    private fun inject() {
        OmdbDemoApplication.appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        (menu?.findItem(R.id.action_search)?.actionView as? SearchView)?.setOnQueryTextListener(object :
                SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchMovie(newText ?: "")
                return true
            }
        })
        return true
    }
}
