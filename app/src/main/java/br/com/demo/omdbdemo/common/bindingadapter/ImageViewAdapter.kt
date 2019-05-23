package br.com.demo.omdbdemo.common.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("android:src")
fun setImageUrl(imageView: ImageView, url: String?) {
    url?.run {
        if (url.isNotEmpty()) {
            Picasso.get().load(this).into(imageView)
        }
    }
}