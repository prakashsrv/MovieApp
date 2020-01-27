package com.biz2.movieapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.pack.moviemvvm.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable
import com.pack.moviemvvm.data.vo.Result


class MainActivityViewModel(private val moviePageListRepository: MoviePageListRepository):
    ViewModel() {


    private val compositeDisposable = CompositeDisposable()

    val  moviePagedList : LiveData<PagedList<Result>> by lazy {
        moviePageListRepository.fetchLiveMoviePagedList(compositeDisposable)
    }



    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}