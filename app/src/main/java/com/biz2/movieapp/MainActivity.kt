package com.biz2.movieapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.biz2.movieapp.data.api.TheMovieDBInterface
import com.biz2.movieapp.data.api.TheMovieDbClient
import com.biz2.movieapp.ui.MainActivityViewModel
import com.biz2.movieapp.ui.MoviePageListRepository
import com.pack.moviemvvm.data.repository.NetworkState
import com.pack.moviemvvm.data.vo.Result
import com.pack.moviemvvm.ui.popular_movie.PopularMoviePageListAdapter
import kotlinx.android.synthetic.main.activity_main.*
 import kotlin.Comparator


class MainActivity : AppCompatActivity() {


    private lateinit var viewModel: MainActivityViewModel

    lateinit var movirRepository: MoviePageListRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        val apiService: TheMovieDBInterface = TheMovieDbClient.getClient()

        movirRepository= MoviePageListRepository(apiService)

        viewModel=getViewModel()

        val movieAdapter= PopularMoviePageListAdapter(this)

        val gridLayoutManager= GridLayoutManager(this,3)



        rv_movie_list.layoutManager=gridLayoutManager
        rv_movie_list.setHasFixedSize(true)
        rv_movie_list.adapter=movieAdapter

        viewModel.moviePagedList.observe(this, Observer {

            it.sortedBy { it.title }.apply {             movieAdapter.submitList(it)
            }

        })




    }

    private fun getViewModel(): MainActivityViewModel
    {
        return ViewModelProviders.of(this,object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {

                return MainActivityViewModel(movirRepository) as T

            }

        })[MainActivityViewModel::class.java]
    }

}

