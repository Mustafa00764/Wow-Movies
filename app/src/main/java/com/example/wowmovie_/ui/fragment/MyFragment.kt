package com.example.wowmovie_.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.wowmovie_.R
import com.example.wowmovie_.adapter.MyAdapter
import com.example.wowmovie_.data.local.MovieRepository
import com.example.wowmovie_.model.SaveMovie
import com.example.wowmovie_.util.Extensions.isInternetAvailable


class MyFragment : Fragment(R.layout.fragment_my) {
lateinit var movies:ArrayList<SaveMovie>
lateinit var adapter:MyAdapter
lateinit var repository: MovieRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        repository= MovieRepository(requireActivity().application)
        loadMovie()
        adapter = MyAdapter()
        adapter.submitList(movies)
        val rvList = view.findViewById<RecyclerView>(R.id.rv_list)
        rvList.adapter = adapter
        adapter.itemClick = {position ->
            findNavController().navigate(R.id.action_myFragment2_to_moviesFragmentSave,
                bundleOf("moviesSaveId" to movies[position].id!!)
            )
            Log.d("link ", "initViews: ${movies[position].id!!}")
        }

        adapter.deleteItem = {

            try {

                deleteProduct(movies[it].id!!)
                loadMovie()
                adapter.submitList(movies)

            } catch (_: IndexOutOfBoundsException) {
            }

        }


    }

    private fun loadMovie() {
        movies = ArrayList()
       var newmovies=repository.getAllMovies() as ArrayList<SaveMovie>
        movies.clear()
        movies.addAll(newmovies)
    }

    fun deleteProduct(id: Int) {

        repository.deleteById(id)

    }


}