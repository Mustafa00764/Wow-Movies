package com.example.wowmovie_.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.wowmovie_.R
import com.example.wowmovie_.adapter.HomeAdapter
import com.example.wowmovie_.adapter.MovieAdapter
import com.example.wowmovie_.adapter.MovieAdapter2
import com.example.wowmovie_.adapter.MovieAdapter5
import com.example.wowmovie_.data.remote.TMDBClient
import com.example.wowmovie_.model.MainModel
import com.example.wowmovie_.model.Movie
import com.example.wowmovie_.model.PopularRU
import com.example.wowmovie_.model.ResultsItemRU
import com.example.wowmovie_.util.Extensions.isInternetAvailable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PopularMoviesFragment : Fragment(R.layout.fragment_popular_movies) {

    private lateinit var adapter: MovieAdapter5
     var page  = (1..25).random()
    private lateinit var movie:ArrayList<ResultsItemRU>
    private lateinit var moviePopular:ArrayList<MainModel>
    lateinit var progresBar: ProgressBar
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {

        progresBar = view.findViewById(R.id.prgBarPopularMovies)

        movie = ArrayList()
        moviePopular = ArrayList()
        adapter= MovieAdapter5()
        val rv = view.findViewById<RecyclerView>(R.id.rvMovies)
        rv.adapter = adapter
        val back = view.findViewById<ImageView>(R.id.iv_backPopular)
        back.setOnClickListener {
            findNavController().navigate(R.id.action_popularMoviesFragment_to_homeFragmentMyMovie)
        }
        if (isInternetAvailable()){
            loadList()
        }else{
            Toast.makeText(requireContext(), "No internet", Toast.LENGTH_SHORT).show()
        }

        adapter.itemClick1={
            val bundle = Bundle()
            bundle.putString("movie3Id",movie[it].id.toString())
            findNavController().navigate(R.id.action_popularMoviesFragment_to_moviesFragment55,bundle

            )
        }
    }

    private fun loadList() {
        TMDBClient.api.getMoviePopular(page).enqueue(object : Callback<PopularRU> {
            override fun onResponse(call: Call<PopularRU>, response: Response<PopularRU>) {
                if (response.isSuccessful){
                    showLoading()
                    Log.d("@@@@@@", "onResponse: ${response.body()}")
                    response.body()?.results?.forEach {
                        movie.add(it)
                    }
                    adapter.submitLists(movie)
                    hideLoading()
                }else{
                    Log.d("@@@@@@", "onResponse:${response.code()} ")
                    Log.d("@@@@@@", "onResponse:${response.errorBody()} ")
                }
            }

            override fun onFailure(call: Call<PopularRU>, t: Throwable) {
                Log.d("@@@@@@", "onFailure:${t.localizedMessage} : ${t.message} : ${t.cause} ")
            }

        })
    }

    fun showLoading(){
        progresBar.visibility = View.VISIBLE
    }

    fun hideLoading(){
        progresBar.visibility = View.GONE
    }

}