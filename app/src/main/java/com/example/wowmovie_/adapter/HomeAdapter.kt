package com.example.wowmovie_.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wowmovie_.R
import com.example.wowmovie_.model.MainModel
import com.example.wowmovie_.model.Movie
import com.example.wowmovie_.model.Movie3
import com.example.wowmovie_.model.Movie4
import com.example.wowmovie_.model.MovieID


class HomeAdapter(val context: Context,) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    val ITEM_NOW=0
    val ITEM_POPULAR=1
    val ITEM_TOP=2
    val ITEM_UNCOMING=3

    var onClick:((Int) -> Unit)? = null
    var itemClick2: ((Int) -> Unit)? = null
    var itemClick3: ((Int) -> Unit)? = null
    var itemClick4: ((Int) -> Unit)? = null
    var itemClick5: ((Int) -> Unit)? = null


    private val collection =ArrayList<MainModel>()
    fun submitLists(collection: ArrayList<MainModel>) {
        this.collection.addAll(collection)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {

        return if (!collection[position].movieNowPlaingRU.isNullOrEmpty()) ITEM_NOW
        else if (!collection[position].moviePopularRU.isNullOrEmpty()) ITEM_POPULAR
        else if (!collection[position].movieTopRatedRU.isNullOrEmpty()) ITEM_TOP
        else ITEM_UNCOMING





    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View

        if (viewType == ITEM_NOW) {

            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_ludshie_films, parent, false)
            return CollectionViewHolder(view)

        } else if (viewType == ITEM_POPULAR) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_ludshie_films2, parent, false)
            return CollectionViewHolder2(view)

        } else if (viewType == ITEM_TOP) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_ludshie_films3, parent, false)
            return CollectionViewHolder3(view)

        } else {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_ludshie_films4, parent, false)
            return CollectionViewHolder4(view)
        }


//        val view:View
//
//        if (viewType==ITEM_STORY){
//
//            view=LayoutInflater.from(parent.context).inflate(R.layout.item_storiesmain,parent,false)
//            return StoriesViewHolder(view)
//
//        }else{
//
//            view=LayoutInflater.from(parent.context).inflate(R.layout.item_pulication_forvideo,parent,false)
//            return PostViewHolder(view)
//        }
    }

    override fun getItemCount(): Int {
       return collection.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = collection[position]
        if (holder is CollectionViewHolder){
            holder?.apply {
                tvGenreMovie.text = movie.title
                 val adapter= MovieAdapter()
                adapter.submitList(movie.movieNowPlaingRU?: ArrayList())
                rv.adapter = adapter
                adapter.itemClick={
                    onClick?.invoke(movie.movieNowPlaingRU?.get(it)?.id ?:0)
                }
            }
            holder.tvVsePopular.setOnClickListener {
                itemClick4?.invoke(position)
            }
//            holder.apply {
//                rvStories.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//                rvStories.adapter = StoryAdapter(feed.stories!!)
//            }
        }
        if (holder is CollectionViewHolder2){
            holder.apply {
                tvGenreMovie.text = movie.title
                val adapter = MovieAdapter2()
                adapter.submitList(movie.moviePopularRU?: ArrayList())
                rv.adapter = adapter
                adapter.itemClick={
                    onClick?.invoke(movie.moviePopularRU?.get(it)?.id?:0)
                }
            }
            holder.tvVsePopular.setOnClickListener {
                itemClick2?.invoke(position)
            }

        }
        if (holder is CollectionViewHolder3){
            holder.apply {
                tvGenreMovie.text = movie.title
                val adapter = MovieAdapter3()
                adapter.submitList(movie.movieTopRatedRU?: ArrayList())
                rv.adapter = adapter
                adapter.itemClick = {
                    onClick?.invoke(movie.movieTopRatedRU?.get(it)?.id?:0)
                }
            }
            holder.tvVsePopular.setOnClickListener {
                itemClick3?.invoke(position)
            }

        }
        if (holder is CollectionViewHolder4){
            holder.apply {
                tvGenreMovie.text = movie.title
                val adapter = MovieAdapter4()
                adapter.submitList(movie.movieUpcomingRU?:ArrayList())
                rv.adapter = adapter
                adapter.itemClick = {
                    onClick?.invoke(movie.movieUpcomingRU?.get(it)?.id?:0)
                }
            }
            holder.tvVsePopular.setOnClickListener {
                itemClick5?.invoke(position)
            }
        }


    }


    inner class CollectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvGenreMovie = itemView.findViewById<TextView>(R.id.tvGenreMovie11)
        val tvVsePopular = itemView.findViewById<TextView>(R.id.tv_vse_ludshie_films11)


        val rv = itemView.findViewById<RecyclerView>(R.id.rvMovieChild11)


    }


    inner class CollectionViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvGenreMovie = itemView.findViewById<TextView>(R.id.tvGenreMovie22)
        val tvVsePopular = itemView.findViewById<TextView>(R.id.tv_vse_ludshie_films222)

        val rv = itemView.findViewById<RecyclerView>(R.id.rvMovieChild22)




    }


    inner class CollectionViewHolder3(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvGenreMovie = itemView.findViewById<TextView>(R.id.tvGenreMovie3)
        val tvVsePopular = itemView.findViewById<TextView>(R.id.tv_vse_ludshie_films33)

        val rv = itemView.findViewById<RecyclerView>(R.id.rvMovieChild3)




    }


    inner class CollectionViewHolder4(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvGenreMovie = itemView.findViewById<TextView>(R.id.tvGenreMovie4)
        val tvVsePopular = itemView.findViewById<TextView>(R.id.tv_vse_ludshie_films44)

        val rv = itemView.findViewById<RecyclerView>(R.id.rvMovieChild4)




    }


//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
//        val view :View
//         view=   LayoutInflater.from(parent.context).inflate(R.layout.item_ludshie_films, parent, false)
//        return CollectionViewHolder(view)
//
//    }
//
//    override fun getItemCount():Int{
//        return collectiones.size
//
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        return collection3.size
//        return collection.size
//        return collections.size
//    }
//
//
//    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
//        val collectiones = collectiones[position]
//        val collection3 = collection3[position]
//        val collection = collection[position]
//        val collections = collections[position]
//
//
//
//
//            holder.tvGenreMovie2.text = collections.title
//            holder.rvMovieChild2.adapter = MovieAdapter2(collections.moviePopular)
//
//
//
//
//            holder.tvGenreMovie.text = collection.title
//        adapter=MovieAdapter(context,collection.movieModels)
//            holder.rvMovieChild.adapter = adapter
//        adapter.itemClick={
//            onClick?.invoke(position)
//        }
//
//
//
//
//            holder.tvGenreMovie3.text = collection3.title
//            holder.rvMovieChild3.adapter = MovieAdapter3(collection3.movieTopResult)
//
//
//
//
//            holder.tvGenreMovie4.text = collectiones.title
//        MovieAdapter4(collectiones.movieUpcoming).also { holder.rvMovieChild4.adapter = it }
//
//
//
//
//    }




}




