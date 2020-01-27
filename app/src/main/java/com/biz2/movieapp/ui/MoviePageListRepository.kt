package com.biz2.movieapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.biz2.movieapp.data.api.POST_PER_PAGE
import com.biz2.movieapp.data.api.TheMovieDBInterface
import com.biz2.movieapp.data.repository.MovieDataSource
import com.biz2.movieapp.data.repository.MovieDataSourceFactory
import com.pack.moviemvvm.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable
import com.pack.moviemvvm.data.vo.Result


class MoviePageListRepository(private val apiservice: TheMovieDBInterface) {

    lateinit var moviePagedList:LiveData<PagedList<Result>>
    lateinit var movieDataSourceFactory: MovieDataSourceFactory

    fun fetchLiveMoviePagedList (compositeDisposable: CompositeDisposable) : LiveData<PagedList<Result>> {

        movieDataSourceFactory = MovieDataSourceFactory(apiservice, compositeDisposable)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()

        moviePagedList = LivePagedListBuilder(movieDataSourceFactory, config).build()




        return moviePagedList
    }



}