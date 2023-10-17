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
import com.example.wowmovie_.adapter.MovieAdapterVideo
import com.example.wowmovie_.data.local.MovieRepository
import com.example.wowmovie_.data.remote.TMDBClient
import com.example.wowmovie_.model.MainModel
import com.example.wowmovie_.model.MovieID

import com.example.wowmovie_.model.MovieViewModel
import com.example.wowmovie_.model.NowItemRU
import com.example.wowmovie_.model.PopularRU
import com.example.wowmovie_.model.ResultsItem
import com.example.wowmovie_.model.ResultsItemRU
import com.example.wowmovie_.model.SaveMovie
import com.example.wowmovie_.model.Video
import com.example.wowmovie_.model.VideoItem
import com.example.wowmovie_.model.VideosItem
import com.example.wowmovie_.model.VideosUS
import com.example.wowmovie_.util.Extensions.isInternetAvailable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MoviesFragment : Fragment(R.layout.fragment_movies) {
    lateinit var repository: MovieRepository
    var id = ""
    val args: MoviesFragmentArgs by navArgs()
    lateinit var main: ArrayList<ResultsItemRU>
    lateinit var movies: ArrayList<MainModel>
    lateinit var adapter: HomeAdapter
    lateinit var adapter2: MovieAdapterVideo

    val randomNumber = (1..100).random()
    private lateinit var tvtitle:TextView
    private lateinit var tvTime:TextView
     var gg:ResultsItem? = null
    private lateinit var tvData:TextView
    private lateinit var tvPlay:TextView
    private lateinit var tvName:TextView
    lateinit var videos:ArrayList<VideosItem>
    var videoKey = ""
    lateinit var progresBar:ProgressBar


    private val videoid=ArrayList<VideoItem>()
    private lateinit var tvopisanie:TextView

    private lateinit var tvopisanie1:Video
    private lateinit var tvball:TextView
    lateinit var ll_click : LinearLayout
    private lateinit var image:ImageView
    private lateinit var ivSave:ImageView
    private lateinit var image2:ImageView
    private lateinit var ll : LinearLayout
    private lateinit var viewM:MovieViewModel


    lateinit var opisanie:ArrayList<NowItemRU>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {

        progresBar = view.findViewById(R.id.prgBarMovies)


        id=args.movieId
        main = ArrayList()
        movies = ArrayList()
        videos = ArrayList()
        gg = ResultsItem()
        adapter2 = MovieAdapterVideo()
        tvPlay = view.findViewById(R.id.tv_play00764)
        tvName = view.findViewById(R.id.tv_Movie_NAME)
        tvTime = view.findViewById(R.id.tv_time)
        tvData = view.findViewById(R.id.tv_Movie_data)
        tvopisanie = view.findViewById(R.id.tv_opisanie)
        image = view.findViewById(R.id.iv_movie_image223)
        image2 = view.findViewById(R.id.iv_movie_image224)
        tvball = view.findViewById(R.id.tv_ball123)
        repository=MovieRepository(requireActivity().application)
        ivSave = view.findViewById(R.id.iv_Save)


            adapter = HomeAdapter(requireContext())
        ll_click = view.findViewById(R.id.ll_1)
        if (isInternetAvailable()){
            loadList()
        }else{
            Toast.makeText(requireContext(), "No internet", Toast.LENGTH_SHORT).show()
        }


        val rvMovieChild007 = view.findViewById<RecyclerView>(R.id.rvMovieChild007)
        rvMovieChild007.adapter = adapter

        val back = view.findViewById<ImageView>(R.id.iv_back555)
        back.setOnClickListener {
            findNavController().navigate(R.id.action_moviesFragment_to_homeFragmentMyMovie)
        }


//            activity?.let{
//                val intent = Intent (Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=${videos}"))
//                Log.d("link ", "initViews: ${fetchVideoMovie()}")
//                it.startActivity(intent)
//            }}
//            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/${video(body = id!!)"))
//            startActivity(intent)
//        }
//        <TouchableOpacity onPress={() => {
//            Linking.openURL('https://www.youtube.com/watch?v=${details.key}');
//        }}
//
//        переведи на kotlin

        adapter.onClick={
            findNavController().navigate(R.id.action_moviesFragment_self,
                bundleOf("movieId" to it.toString())
            )
        }



        fetchVideoMovie()

    }

//    private fun fetchNowPlayingMovie() {
//
//        Log.d("@@@@@@", "fetchNowPlayingMovie: ")
//        TMDBClient.api.getVideos(id.toInt()).enqueue(object :Callback<Videos>{
//            override fun onResponse(call: Call<Videos>, response: Response<Videos>) {
//                if (response.isSuccessful){
//                    Log.d("@@@@@@", "onResponse: ${response.body()}")
//                    response.body()?.results?.forEach {
//
//                    }
//                    videos.clear()
//                    movies.add(MainModel("Популярные", movies = videos))
//                    adapter.submitLists(movies)
//
//                }else{
//                    Log.d("@@@@@@", "onResponse:${response.code()} ")
//                    Log.d("@@@@@@", "onResponse:${response.errorBody()} ")
//                }
//            }
//
//            override fun onFailure(call: Call<Videos>, t: Throwable) {
//                Log.d("@@@@@@", "onFailure:${t.localizedMessage} : ${t.message} : ${t.cause} ")
//            }
//
//        })
//
//
//    }
    private fun fetchNowPlayingMovie() {

        Log.d("@@@@@@", "fetchNowPlayingMovie: ")
        TMDBClient.api.getMoviePopular(randomNumber).enqueue(object :Callback<PopularRU>{
            override fun onResponse(call: Call<PopularRU>, response: Response<PopularRU>) {
                if (response.isSuccessful){
                    Log.d("@@@@@@", "onResponse: ${response.body()}")
                    response.body()?.results?.forEach {
                        main.add(it)
                    }
                    movies.clear()
                    movies.add(MainModel("Популярные", moviePopularRU = main))
                    adapter.submitLists(movies)

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
    private fun fetchVideoMovie() {

        Log.d("@@@@@@", "fetchNowPlayingMovie: ")
        TMDBClient.api.getVideos(id.toInt()).enqueue(object :Callback<VideosUS>{
            override fun onResponse(call: Call<VideosUS>, response: Response<VideosUS>) {
                if (response.isSuccessful)
                    response.body()?.results?.getOrNull(0)?.key.toString()
                        .also {videoKey = it
                    }
                else{
                    Log.d("@@@@@@", "onResponse:${response.code()} ")
                    Log.d("@@@@@@", "onResponse:${response.errorBody()} ")
                }
            }

            override fun onFailure(call: Call<VideosUS>, t: Throwable) {
                Log.d("@@@@@@", "onFailure:${t.localizedMessage} : ${t.message} : ${t.cause} ")
            }

        })


    }





    private fun loadList() {

        TMDBClient.api.getAllProducts(id.toInt()).enqueue(object :Callback<MovieID>{
            override fun onResponse(call: Call<MovieID>, response: Response<MovieID>) {

                if (response.isSuccessful){
                    showLoading()

                    setData(response.body())

                }
                fetchNowPlayingMovie()

                ll_click.setOnClickListener {
                        val intent = Intent (Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=${videoKey}"))
                        Log.d("link ", "onResponse: ${videoKey}")
                        startActivity(intent)
                    }
                hideLoading()


            }

            override fun onFailure(call: Call<MovieID>, t: Throwable) {

            }
        })

    }

    private fun setData(body: MovieID?) {

        tvName.text=body?.title
        tvTime.text = body?.runtime.toString()
        Glide.with(image).load("https://image.tmdb.org/t/p/w500${body?.backdropPath}").into(image)
        Glide.with(image2).load("https://image.tmdb.org/t/p/w500${body?.posterPath}").into(image2)

        tvopisanie.text = body?.overview
        tvData.text = body?.releaseDate
        tvball.text=body?.voteAverage.toString()

        ivSave.setOnClickListener {
            val MOVIESid = id.toInt()
            val image = body?.posterPath.toString()
            val title = body?.title
            val time = body?.releaseDate
            val ball = body?.voteAverage
            val sAVE = SaveMovie(MOVIESid,image,title.toString(),time.toString(),ball.toString())
            repository.saveMovies(sAVE)
            Toast.makeText(requireContext(), "Фильм сохранён", Toast.LENGTH_SHORT).show()
        }

    }

    fun showLoading(){
       progresBar.visibility = View.VISIBLE
    }

    fun hideLoading(){
        progresBar.visibility = View.GONE
    }




}