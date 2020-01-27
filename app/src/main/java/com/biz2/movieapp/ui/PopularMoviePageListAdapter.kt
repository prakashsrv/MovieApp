package com.pack.moviemvvm.ui.popular_movie

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.biz2.movieapp.R
import com.biz2.movieapp.data.api.BASE_POSTER_PATH
import com.bumptech.glide.Glide
 import com.pack.moviemvvm.data.repository.NetworkState
 import com.pack.moviemvvm.data.vo.Result
 import kotlinx.android.synthetic.main.movie_list_item.view.*
import kotlinx.android.synthetic.main.network_state_item.view.*

class PopularMoviePageListAdapter(public val context: Context) : PagedListAdapter<Result,RecyclerView.ViewHolder>(MovieDiffCallback()) {

    val MOVIE_VIEW_TYPE = 1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View

        view = layoutInflater.inflate(R.layout.movie_list_item, parent, false)
        return MovieItemViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as MovieItemViewHolder).bind(getItem(position), context)
    }


    class MovieDiffCallback : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {

            return oldItem == newItem
        }

    }


    class MovieItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: Result?, context: Context) {
            itemView.cv_movie_title.text = movie?.title
            itemView.cv_movie_release_date.text = movie?.releaseDate

            val moviePosterURL = BASE_POSTER_PATH + movie?.posterPath
            Glide.with(itemView.context)
                .load(moviePosterURL)
                .into(itemView.cv_iv_movie_poster)


        }
    }
}





