package br.com.demo.omdbdemo.feature.home.view

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import br.com.demo.omdbdemo.OmdbDemoApplication
import br.com.demo.omdbdemo.R
import br.com.demo.omdbdemo.databinding.ActivityHomeBinding
import br.com.demo.omdbdemo.di.ViewModelFactory
import br.com.demo.omdbdemo.feature.home.viewmodel.HomeViewModel
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        OmdbDemoApplication.appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        val binding = DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home)
        binding.viewModel = viewModel
        viewModel.liveDataMediator.observe(this, androidx.lifecycle.Observer {

        })
        setSupportActionBar(binding.mainToolbar)
//        startActivity(Intent(this, MovieDetailActivity::class.java).apply {
//            putExtra(MovieDetailActivity.MOVIE_ARG, Movie(title = "Avengers: Endgame*", imdbId = "tt4154796"))
//        })
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
