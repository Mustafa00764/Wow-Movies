package com.example.wowmovie_.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.wowmovie_.R
import com.example.wowmovie_.adapter.HomeAdapter
import com.example.wowmovie_.adapter.MovieAdapter6
import com.example.wowmovie_.data.remote.TMDBClient
import com.example.wowmovie_.model.MainModel
import com.example.wowmovie_.model.Movie
import com.example.wowmovie_.model.PopularRU
import com.example.wowmovie_.model.ResultsItemRU
import com.example.wowmovie_.model.TopRatedRU
import com.example.wowmovie_.model.TopRatedRuItem
import com.example.wowmovie_.util.Extensions.isInternetAvailable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TopMoviesFragment : Fragment(R.layout.fragment_top_movies) {

    private lateinit var adapter:MovieAdapter6
     var page  = (1..25).random()
    private lateinit var movie:ArrayList<TopRatedRuItem>
    private lateinit var moviePopular:ArrayList<MainModel>
    lateinit var progresBar: ProgressBar
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {

        progresBar = view.findViewById(R.id.prgBarTopMovies)

        movie = ArrayList()
        adapter= MovieAdapter6()
        val rv = view.findViewById<RecyclerView>(R.id.rvMovies)
        rv.adapter = adapter
        if (isInternetAvailable()){
            loadList()
        }else{
            Toast.makeText(requireContext(), "No internet", Toast.LENGTH_SHORT).show()
        }
        val back = view.findViewById<ImageView>(R.id.iv_backTop)
        back.setOnClickListener {
            findNavController().navigate(R.id.action_topMoviesFragment_to_homeFragmentMyMovie)
        }
        adapter.itemClick1={
            val bundle = Bundle()
            bundle.putString("topmovieId",movie[it].id.toString())
            findNavController().navigate(R.id.action_topMoviesFragment_to_moviesFragment66,bundle

            )
        }
    }

    private fun loadList() {
        TMDBClient.api.getMovieRTopResult(page).enqueue(object : Callback<TopRatedRU> {
            override fun onResponse(call: Call<TopRatedRU>, response: Response<TopRatedRU>) {
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

            override fun onFailure(call: Call<TopRatedRU>, t: Throwable) {
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