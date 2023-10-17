package com.example.wowmovie_.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.wowmovie_.R
import com.example.wowmovie_.adapter.MovieAdapter5
import com.example.wowmovie_.data.remote.TMDBClient
import com.example.wowmovie_.model.Movie4
import com.example.wowmovie_.model.Movie5
import com.example.wowmovie_.model.TopRatedRU
import com.example.wowmovie_.model.TopRatedRuItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeSerialFragment : Fragment(R.layout.fragment_home_serial) {
lateinit var adapter:MovieAdapter5
lateinit var movie:ArrayList<TopRatedRuItem>
lateinit var movies:ArrayList<Movie5>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        initViews2(view)
        initViews(view)
//        initViews3(view)
//        initViews4(view)
    }

    private fun initViews(view: View) {
        adapter = MovieAdapter5()
        movie = ArrayList()
        loadList()
//        loadList2()
//        loadList3()
//        loadList4()

    }

    private fun loadList() {
        TMDBClient.api.getMovieRTopResult(1).enqueue(object :Callback<TopRatedRU>{
            override fun onResponse(call: Call<TopRatedRU>, response: Response<TopRatedRU>) {
                if (response.isSuccessful){
                    movie.clear()
                    response.body()?.results?.forEach {
                        movie.add(it)
                    }
//                    movieUpcoming.add(Movie4("Предстоящие", movie4))
//                    adapter.submitLists4(movieUpcoming)


                }
            }

            override fun onFailure(call: Call<TopRatedRU>, t: Throwable) {

            }

        })
    }

//    private fun initViews2(view: View) {
//        adapter = MovieAdapter5(ArrayList())
//        movie = ArrayList()
//       val rv2 = view.findViewById<RecyclerView>(R.id.rv_samiy_popular)
//        rv2.adapter = adapter
//        loadList2()
//
//    }
//
//    private fun loadList2() {
//        TMDBClient.api.getMovieRTopResult(2).enqueue(object :Callback<TopRatedRU>{
//            override fun onResponse(call: Call<TopRatedRU>, response: Response<TopRatedRU>) {
//                if (response.isSuccessful){
//                    movie.clear()
//                    response.body()?.results?.forEach {
//                        movie.add(it)
//                    }
//
//                    adapter.submitLists(movie)
//                }
//            }
//
//            override fun onFailure(call: Call<TopRatedRU>, t: Throwable) {
//
//            }
//
//        })
//    }
//    private fun initViews3(view: View) {
//        adapter = MovieAdapter5(ArrayList())
//        movie = ArrayList()
//       val rv3 = view.findViewById<RecyclerView>(R.id.rv_samiy_popular)
//        rv3.adapter = adapter
//        loadList3()
//
//    }
//
//    private fun loadList3() {
//        TMDBClient.api.getMovieRTopResult(3).enqueue(object :Callback<TopRatedRU>{
//            override fun onResponse(call: Call<TopRatedRU>, response: Response<TopRatedRU>) {
//                if (response.isSuccessful){
//                    movie.clear()
//                    response.body()?.results?.forEach {
//                        movie.add(it)
//                    }
//                    adapter.submitLists(movie)
//                }
//            }
//
//            override fun onFailure(call: Call<TopRatedRU>, t: Throwable) {
//
//            }
//
//        })
//    }
//    private fun initViews4(view: View) {
//        adapter = MovieAdapter5(ArrayList())
//        movie = ArrayList()
//       val rv4 = view.findViewById<RecyclerView>(R.id.rv_samiy_popular)
//        rv4.adapter = adapter
//        loadList4()
//
//    }
//
//    private fun loadList4() {
//        TMDBClient.api.getMovieRTopResult(4).enqueue(object :Callback<TopRatedRU>{
//            override fun onResponse(call: Call<TopRatedRU>, response: Response<TopRatedRU>) {
//                if (response.isSuccessful){
//                    movie.clear()
//                    response.body()?.results?.forEach {
//                        movie.add(it)
//                    }
//
//                    adapter.submitLists(movie)
//                }
//            }
//
//            override fun onFailure(call: Call<TopRatedRU>, t: Throwable) {
//
//            }
//
//        })
//    }

}