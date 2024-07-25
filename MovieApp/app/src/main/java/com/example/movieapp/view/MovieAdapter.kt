package com.example.movieapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.MovieListItemBinding
import com.example.movieapp.model.Movie
import com.example.movieapp.view.MovieAdapter.MovieViewHolder

class MovieAdapter(private val context: Context, private val movieArrayList: ArrayList<Movie>) :
    RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding: MovieListItemBinding = DataBindingUtil.inflate<MovieListItemBinding>
        LayoutInflater.from(parent.context),
        R.layout.movie_list_item,
        parent,
        false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieArrayList[position]
        holder.movieListItemBinding.movie = movie
    }

    override fun getItemCount(): Int {
        return movieArrayList.size
    }

    inner class MovieViewHolder(internal val movieListItemBinding: MovieListItemBinding) :
        RecyclerView.ViewHolder(
            movieListItemBinding.root
        ) {
        init {
            movieListItemBinding.root.setOnClickListener { }
        }
    }
}
