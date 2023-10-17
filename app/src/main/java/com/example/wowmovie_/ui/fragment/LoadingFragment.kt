package com.example.wowmovie_.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.wowmovie_.R
import com.example.wowmovie_.adapter.MovieAdapter5
import com.example.wowmovie_.adapter.MovieAdapterSearch
import com.example.wowmovie_.data.remote.TMDBClient
import com.example.wowmovie_.model.MainModel
import com.example.wowmovie_.model.Search
import com.example.wowmovie_.model.SearchItem
import com.example.wowmovie_.util.Extensions.isInternetAvailable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query

class LoadingFragment : Fragment(R.layout.fragment_loading) {
    lateinit var adapter: MovieAdapterSearch
    lateinit var movie:ArrayList<SearchItem>
    lateinit var progresBar: ProgressBar


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) {

        progresBar = view.findViewById(R.id.prgBarLoadingMovies)

        adapter = MovieAdapterSearch()
        movie = ArrayList()
       val et = view.findViewById<EditText>(R.id.etSearch_search)
        val rv = view.findViewById<RecyclerView>(R.id.rvSearch_search)
        rv.adapter = adapter
        et.addTextChangedListener {
            loadList(it.toString())
        }
        if (isInternetAvailable()){
            loadList(query = String())
        }else{
            Toast.makeText(requireContext(), "No internet", Toast.LENGTH_SHORT).show()
        }
        adapter.itemClick1={
            val bundle = Bundle()
            bundle.putString("searchmovieId",movie[it].id.toString())
            findNavController().navigate(R.id.action_loadingFragment_to_moviesFragment7,bundle

            )
        }


    }

    private fun loadList(query: String) {
        TMDBClient.api.getSearch(query = query).enqueue(object :Callback<Search>{
            override fun onResponse(call: Call<Search>, response: Response<Search>) {
                if (response.isSuccessful){
                    showLoading()
                    movie.clear()
                    response.body()?.results?.forEach {
                        movie.add(it)
                    }
                    adapter.submitLists(movie)
                    hideLoading()

//                    movies.clear()
//                    movies.add(MainModel("Популярные", moviePopularRU = main))
//                    adapter.submitLists(movies)

//                    response.body()?.results?.forEach {
//                        main.add(it)
//                    }
                }

            }

            override fun onFailure(call: Call<Search>, t: Throwable) {

            }

        })
    }


    fun showLoading(){
        progresBar.visibility = View.VISIBLE
    }

    fun hideLoading(){
        progresBar.visibility = View.GONE
    }
//    private fun searchMovies(query: String) {
//        val retrofitInstance = RetrofitInstance.getRetrofitInstance()
//        val tmdbApi = retrofitInstance.create(TMDBApi::class.java)
//
//        tmdbApi.searchMovies("YOUR_API_KEY_HERE", query).enqueue(object : Callback<MovieSearchResponse> {
//            override fun onResponse(call: Call<MovieSearchResponse>, response: Response<MovieSearchResponse>) {
//                val movies = response.body()?.movies
//
//                if (movies != null) {
//                    // Обновите ваш интерфейс с новыми результатами поиска
//                    updateResults(movies)
//                } else {
//                    // Покажите сообщение или обработайте ошибку получения данных
//                    handleErrorLoadingResults()
//                }
//            }
//
//            override fun onFailure(call: Call<MovieSearchResponse>, t: Throwable) {
//                // Покажите сообщение или обработайте ошибку при неудачном выполнении запроса
//                handleSearchFailure()
//            }
//        })
}