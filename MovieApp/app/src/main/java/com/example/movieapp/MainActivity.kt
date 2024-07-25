package com.example.movieapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.model.Movie
import com.example.movieapp.view.MovieAdapter
import com.example.movieapp.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private var movies: ArrayList<Movie?>? = null
    private var recyclerView: RecyclerView? = null
    private var movieAdapter: MovieAdapter? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var binding: ActivityMainBinding? = null
    private var viewModel: MainActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        viewModel = ViewModelProvider(this)
            .get(MainActivityViewModel::class.java)

        popularMovies

        swipeRefreshLayout = binding.swipeLayout
        swipeRefreshLayout!!.setColorSchemeResources(R.color.black)
        swipeRefreshLayout!!.setOnRefreshListener(object : OnRefreshListener {
            override fun onRefresh() {
                this.popularMovies
            }
        })
    }

    private val popularMovies: Unit
        get() {
            viewModel!!.allMovies.observe(this, object : Observer<List<Movie?>?> {
                override fun onChanged(moviesFromLiveData: List<Movie?>) {
                    movies = moviesFromLiveData as ArrayList<Movie?>
                    displayMoviesInRecyclerView()
                }
            })
        }

    private fun displayMoviesInRecyclerView() {
        recyclerView = binding!!.recyclerView

        movieAdapter = MovieAdapter(this, movies)

        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = movieAdapter

        recyclerView!!.layoutManager = GridLayoutManager(this, 2)

        // Notify an adapter associated with a RecyclerView
        // that the underlying dataset has changed
        movieAdapter!!.notifyDataSetChanged()
    }
}