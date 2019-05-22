package br.com.demo.omdbdemo.feature.detail.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import br.com.demo.omdbdemo.R
import br.com.demo.omdbdemo.databinding.ActivityMovieDetailBinding
import br.com.demo.omdbdemo.domain.model.Movie
import br.com.demo.omdbdemo.feature.detail.viewmodel.MovieDetailViewModel

class MovieDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityMovieDetailBinding

    private val movie: Movie by lazy { intent.getParcelableExtra<Movie>(MOVIE_ARG) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)
        binding.viewModel = ViewModelProviders.of(this)[MovieDetailViewModel::class.java]
        binding.lifecycleOwner = this
        binding.viewModel?.setup(movie)

    }

    companion object {
        const val MOVIE_ARG = "MOVIE_ARG"
    }
}
