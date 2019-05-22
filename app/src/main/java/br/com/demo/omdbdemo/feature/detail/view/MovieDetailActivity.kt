package br.com.demo.omdbdemo.feature.detail.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.demo.omdbdemo.R
import br.com.demo.omdbdemo.databinding.ActivityMovieDetailBinding
import com.google.android.material.snackbar.Snackbar

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMovieDetailBinding>(this, R.layout.activity_movie_detail)
        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

}
