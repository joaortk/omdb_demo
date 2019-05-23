package br.com.demo.omdbdemo.feature.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.demo.omdbdemo.databinding.MovieItemBinding
import br.com.demo.omdbdemo.domain.model.Movie
import br.com.demo.omdbdemo.feature.home.viewmodel.MovieItemViewModel

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    var listener: MoviesAdapterListener? = null
    var items: List<MovieItemViewModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieItemViewModel) {
            with(binding) {
                root.setOnClickListener {
                    listener?.didSelectMovie(item.movie)
                }
                movie = item
            }
        }
    }
}

interface MoviesAdapterListener {
    fun didSelectMovie(movie: Movie)
}