package com.example.wowmovie_.ui.fragment.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.wowmovie_.R
import com.example.wowmovie_.adapter.HomeAdapter

import com.example.wowmovie_.data.remote.TMDBClient
import com.example.wowmovie_.model.MainModel

import com.example.wowmovie_.model.NowItemRU
import com.example.wowmovie_.model.NowPlaingRU

import com.example.wowmovie_.model.PopularRU
import com.example.wowmovie_.model.ResultsItemRU
import com.example.wowmovie_.model.TopRatedRU
import com.example.wowmovie_.model.TopRatedRuItem

import com.example.wowmovie_.model.UpcomingRU
import com.example.wowmovie_.model.UpcomingRUItem
import com.example.wowmovie_.util.Extensions.isInternetAvailable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragmentMyMovie : Fragment(R.layout.fragment_home_my_movie) {
    lateinit var movie: ArrayList<NowItemRU>
    lateinit var main:ArrayList<ResultsItemRU>
    lateinit var movie3:ArrayList<TopRatedRuItem>
    lateinit var movie4:ArrayList<UpcomingRUItem>
    lateinit var tvVsePopular:TextView
    lateinit var tvVseTop:TextView
    lateinit var tvVseUpcoming:TextView
    lateinit var tvVseNow:TextView
    lateinit var progresBar: ProgressBar
    var page = 1
    lateinit var movies: ArrayList<MainModel>

    lateinit var adapter: HomeAdapter
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        Click(view)



    }


    private fun Click(view: View){
            (R.layout.item_ludshie_films)


//        tvVseTop = view.findViewById(R.id.tv_vse_ludshie_films33)
//        tvVseUpcoming = view.findViewById(R.id.tv_vse_ludshie_films44)
//        tvVseNow = view.findViewById(R.id.tv_vse_ludshie_films11)
//
//        tvVseNow.setOnClickListener {
//            findNavController().navigate(R.id.action_homeFragmentMyMovie_to_nowMoviesFragment)
//        }
//        tvVsePopular.setOnClickListener {
//            findNavController().navigate(R.id.action_homeFragmentMyMovie_to_popularMoviesFragment)
//        }
//        tvVseTop.setOnClickListener {
//            findNavController().navigate(R.id.action_homeFragmentMyMovie_to_topMoviesFragment)
//        }
//        tvVseUpcoming.setOnClickListener {
//            findNavController().navigate(R.id.action_homeFragmentMyMovie_to_upcomingMoviesFragment)
//        }

    }


    private fun initView(view: View) {
        val rvMain2 = view.findViewById<RecyclerView>(R.id.rvMain)

        progresBar = view.findViewById(R.id.prgBarHomeMovies)

        main = ArrayList()
        movie = ArrayList()
        movie3 = ArrayList()
        movie4 = ArrayList()
        movies = ArrayList()
        adapter = HomeAdapter(requireContext())

        rvMain2.adapter = adapter
        if (isInternetAvailable()){
            fetchNowPlayingMovies()
        }else{
            Toast.makeText(requireContext(), "No internet", Toast.LENGTH_SHORT).show()
        }




        adapter.itemClick2={
            findNavController().navigate(R.id.action_homeFragmentMyMovie_to_popularMoviesFragment)
        }

        adapter.itemClick3={
            findNavController().navigate(R.id.action_homeFragmentMyMovie_to_topMoviesFragment)
        }
        adapter.itemClick4={
            findNavController().navigate(R.id.action_homeFragmentMyMovie_to_nowMoviesFragment)
        }
        adapter.itemClick5={
            findNavController().navigate(R.id.action_homeFragmentMyMovie_to_upcomingMoviesFragment)
        }



        
        adapter.onClick={
            findNavController().navigate(R.id.action_homeFragmentMyMovie_to_moviesFragment,
                bundleOf("movieId" to it.toString())
            )
        }

    }
    private fun fetchNowPlayingMovie() {

        Log.d("@@@@@@", "fetchNowPlayingMovie: ")
        TMDBClient.api.getMoviePopular(page = page).enqueue(object :Callback<PopularRU>{
            override fun onResponse(call: Call<PopularRU>, response: Response<PopularRU>) {

                if (response.isSuccessful){

                    Log.d("@@@@@@", "onResponse: ${response.body()}")
                    response.body()?.results?.forEach {
                        main.add(it)
                    }
                    movies.clear()
                    movies.add(MainModel("Популярные", moviePopularRU = main))
                    adapter.submitLists(movies)

                    fetchNowPlayingMovie3()
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

    private fun fetchNowPlayingMovie4() {
        TMDBClient.api.getMovieUpcoming(10).enqueue(object :Callback<UpcomingRU>{
            override fun onResponse(call: Call<UpcomingRU>, response: Response<UpcomingRU>) {
                if (response.isSuccessful){
                    movie4.clear()
                    response.body()?.results?.forEach {
                        movie4.add(it)
                    }
                    movies.clear()
                    movies.add(MainModel("Предстоящие", movieUpcomingRU = movie4))
                    adapter.submitLists(movies)
                }else{
                    Log.d("@@@@@@", "onResponse:${response.code()} ")
                    Log.d("@@@@@@", "onResponse:${response.errorBody()} ")

                }
            }

            override fun onFailure(call: Call<UpcomingRU>, t: Throwable) {

            }

        })
    }
    
    private fun fetchNowPlayingMovie3() {
        TMDBClient.api.getMovieRTopResult(2).enqueue(object :Callback<TopRatedRU>{
            override fun onResponse(call: Call<TopRatedRU>, response: Response<TopRatedRU>) {
                if (response.isSuccessful){
                    movie3.clear()
                    response.body()?.results?.forEach {
                        movie3.add(it)
                    }
                    movies.clear()
                    movies.add(MainModel("Самые популярные", movieTopRatedRU = movie3))
                    adapter.submitLists(movies)
                    fetchNowPlayingMovie4()
                }else{
                    Log.d("@@@@@@", "onResponse:${response.code()} ")
                    Log.d("@@@@@@", "onResponse:${response.errorBody()} ")
                }
            }

            override fun onFailure(call: Call<TopRatedRU>, t: Throwable) {

            }

        })
    }
    
    private fun fetchNowPlayingMovies() {

        Log.d("@@@@@@", "fetchNowPlayingMovies: ")
        TMDBClient.api.getNowPlayingMovies(15).enqueue(object : Callback<NowPlaingRU> {
            override fun onResponse(
                call: Call<NowPlaingRU>,
                response: Response<NowPlaingRU>
            ) {
                if (response.isSuccessful) {
                    showLoading()
//                    ludshiyFilm()
                    movie.clear()
                    Log.d("@@@@@@", "onResponse: ${response.body()}")
                    response.body()?.results?.forEach {
                        movie.add(it)
                    }
                    movies.clear()
                    movies.add(MainModel("Сейчас играют", movieNowPlaingRU = movie))
                    adapter.submitLists(movies)
                    fetchNowPlayingMovie()
                    hideLoading()

                } else {
                    Log.d("@@@@@@", "onResponse:${response.code()} ")
                    Log.d("@@@@@@", "onResponse:${response.errorBody()} ")
                }
            }

            override fun onFailure(call: Call<NowPlaingRU>, t: Throwable) {
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