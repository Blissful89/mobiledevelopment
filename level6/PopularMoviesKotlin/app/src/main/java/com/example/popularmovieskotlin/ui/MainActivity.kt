package com.example.popularmovieskotlin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmovieskotlin.R
import com.example.popularmovieskotlin.adapter.MovieSelectAdapter
import com.example.popularmovieskotlin.model.Movie

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val movies = arrayListOf<Movie>()
    private lateinit var  viewModel: MainActivityViewModel
    private val movieSelectAdapter = MovieSelectAdapter(movies) {movie -> onMovieClick(movie)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setSupportActionBar(toolbar)

        initView()
        initViewModel()
        viewModel.getMoviesForYear("2001")
    }

    private fun initView(){
        rvMovies.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvMovies.adapter = movieSelectAdapter
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.movies.observe(this, Observer {
            movies.clear()
            movies.addAll(it)
            movieSelectAdapter.notifyDataSetChanged()
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun onMovieClick(movie: Movie) {
        println("HELE MOOIE CLICK!")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
