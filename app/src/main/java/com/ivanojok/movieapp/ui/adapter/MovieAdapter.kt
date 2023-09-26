package com.ivanojok.movieapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ivanojok.movieapp.R
import com.ivanojok.movieapp.data.Movie

class MovieAdapter(val context:Context, var movies: List<Movie>): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(view:View): RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.imageView)
        val title = view.findViewById<TextView>(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.movie_item_layout, parent, false)
        return MovieViewHolder(v)
    }

    override fun getItemCount(): Int {
       return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.title.text = movies[position].original_title
        Glide.with(context).load("https://image.tmdb.org/t/p/w500${movies[position].poster_path}").into(holder.image)
    }
}