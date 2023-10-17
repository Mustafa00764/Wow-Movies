package com.example.wowmovie_.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wowmovie_.R
import com.example.wowmovie_.adapter.HomeAdapter
import com.example.wowmovie_.data.local.MovieRepository
import com.example.wowmovie_.data.remote.TMDBClient
import com.example.wowmovie_.model.MainModel
import com.example.wowmovie_.model.MovieID

import com.example.wowmovie_.model.MovieViewModel
import com.example.wowmovie_.model.NowItemRU
import com.example.wowmovie_.model.PopularRU
import com.example.wowmovie_.model.ResultsItemRU
import com.example.wowmovie_.model.SaveMovie
import com.example.wowmovie_.model.VideosUS
import com.example.wowmovie_.util.Extensions.isInternetAvailable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MoviesFragmentSave : Fragment(R.layout.fragment_movies_save) {
    var id = "1"
    val args: MoviesFragmentSaveArgs by navArgs()


    lateinit var repository: MovieRepository
    lateinit var main: ArrayList<ResultsItemRU>
    lateinit var movies: ArrayList<MainModel>
    lateinit var adapter: HomeAdapter
    val randomNumber = (1..10).random()
    private lateinit var tvtitle:TextView
    private lateinit var tvTime:TextView
    private lateinit var tvData:TextView
    private lateinit var tvName:TextView
    private lateinit var tvopisanie:TextView
    private lateinit var tvball:TextView
    private lateinit var image:ImageView
    private lateinit var image2:ImageView
    private lateinit var ll : LinearLayout
    private lateinit var viewM:MovieViewModel
    var videoKey = ""
    lateinit var progresBar: ProgressBar
    private lateinit var ivSave:ImageView






    lateinit var opisanie:ArrayList<NowItemRU>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {

        repository=MovieRepository(requireActivity().application)
        id=args.moviesSaveId.toString()
        progresBar = view.findViewById(R.id.prgBarMovies66)
        main = ArrayList()
        movies = ArrayList()
        tvName = view.findViewById(R.id.tv_Movie_NAMESAVE)
        tvTime = view.findViewById(R.id.tv_timeSAVE)
        tvData = view.findViewById(R.id.tv_Movie_dataSAVE)
        tvopisanie = view.findViewById(R.id.tv_opisanieSAVE)
        image = view.findViewById(R.id.iv_movie_imageSave)
        image2 = view.findViewById(R.id.iv_movie_imageSave1)
        tvball = view.findViewById(R.id.tv_ballSAVE)
        ll = view.findViewById(R.id.ll_Save)




        val back = view.findViewById<ImageView>(R.id.iv_back2226)
        back.setOnClickListener {
            findNavController().navigate(R.id.action_moviesFragmentSave_to_myFragment2)
        }

        if (isInternetAvailable()){
            loadList(id.toInt())
        }else{
            Toast.makeText(requireContext(), "No internet", Toast.LENGTH_SHORT).show()
        }




        fetchVideoMovie()

    }



    private fun fetchVideoMovie() {


        Log.d("@@@@@@", "fetchNowPlayingMovie: ")
        TMDBClient.api.getVideos(id.toInt()).enqueue(object :Callback<VideosUS>{
            override fun onResponse(call: Call<VideosUS>, response: Response<VideosUS>) {
                if (response.isSuccessful)
                    response.body()?.results?.getOrNull(0)?.key.toString()
                        .also {videoKey = it

                }else{
                    Log.d("@@@@@@", "onResponse:${response.code()} ")
                    Log.d("@@@@@@", "onResponse:${response.errorBody()} ")
                }
            }

            override fun onFailure(call: Call<VideosUS>, t: Throwable) {
                Log.d("@@@@@@", "onFailure:${t.localizedMessage} : ${t.message} : ${t.cause} ")
            }

        })


    }

    private fun loadList(id: Int) {
//

        TMDBClient.api.getAllProducts(id).enqueue(object :Callback<MovieID>{

            override fun onResponse(call: Call<MovieID>, response: Response<MovieID>) {

                if (response.isSuccessful){
                    showLoading()

                    setData(response.body()!!)

                }
                ll.setOnClickListener {
                    val intent = Intent (Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=${videoKey}"))
                    Log.d("link ", "initViews: ${fetchVideoMovie()}")
                    startActivity(intent)
                }
                hideLoading()

            }

            override fun onFailure(call: Call<MovieID>, t: Throwable) {

            }
        })

    }

    private fun setData(body: MovieID) {

        tvName.text=body.title!!
        tvTime.text = body.runtime!!.toString()
        Glide.with(image).load("https://image.tmdb.org/t/p/w500${body.posterPath!!}").into(image)
        Glide.with(image2).load("https://image.tmdb.org/t/p/w500${body.backdropPath!!}").into(image2)
        tvopisanie.text = body.overview!!
        tvData.text = body.releaseDate!!
        tvball.text=body.voteAverage!!.toString()



    }

    fun showLoading(){
        progresBar.visibility = View.VISIBLE
    }

    fun hideLoading(){
        progresBar.visibility = View.GONE
    }



}