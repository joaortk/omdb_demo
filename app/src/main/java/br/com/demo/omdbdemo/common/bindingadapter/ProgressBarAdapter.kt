package br.com.demo.omdbdemo.common.bindingadapter

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("app:isLoading")
fun isLoading(progressBar: ProgressBar, isLoading: Boolean) {
    progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
}

@BindingAdapter("app:isLoading")
fun isLoading(recyclerView: RecyclerView, isLoading: Boolean) {
    recyclerView.visibility = if (isLoading) View.GONE else View.VISIBLE
}