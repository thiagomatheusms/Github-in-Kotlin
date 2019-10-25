package com.example.githubKotlin.feature.repositoryK

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.githubKotlin.model.BaseK
import com.example.githubKotlin.model.RepositoryK

class RepositoryKViewModel(private val repositoryKDataSource: RepositoryKDataSource) : ViewModel() {

    private var repositoryPagedList: LiveData<PagedList<RepositoryK>>
    private var baseLiveData: MutableLiveData<BaseK> = MutableLiveData()

    init {
        val config =
            PagedList.Config.Builder().setPageSize(5).setEnablePlaceholders(false).build()

        repositoryPagedList = initializePagedListBuilder(config).build()
        this.baseLiveData.value = BaseK(status = BaseK.STATUS.LOADING)
        baseLiveData = repositoryKDataSource.getBasek()

    }

    fun getPosts(): LiveData<PagedList<RepositoryK>> {
        return repositoryPagedList
    }

    fun getBase() = baseLiveData


    private fun initializePagedListBuilder(config: PagedList.Config): LivePagedListBuilder<Int, RepositoryK> {
        val dataSourceFactory = object : DataSource.Factory<Int, RepositoryK>() {
            override fun create(): DataSource<Int, RepositoryK> {

                Log.i("BASE", baseLiveData.value?.status.toString())
                return repositoryKDataSource
            }
        }

        return LivePagedListBuilder(dataSourceFactory, config)
    }


    //    private val baseData: MutableLiveData<BaseK> = MutableLiveData()
//
//
//    fun observerBaseRepository() = baseData

    //
//    private fun setLoading() {
//        this.baseData.value?.status = BaseK.STATUS.LOADING
//    }

//    fun success(baseK: BaseK){
//        baseK.status = BaseK.STATUS.SUCCESS
//        this.baseData.postValue(baseK)
//    }

    //
//
//    private fun getBase() {
//        //setar estado de loading
//        setLoading()
//        //fazer a request
//
//    }
//
//    private fun success(baseK: BaseK?) {
//        baseK?.let {
//            it.status = BaseK.STATUS.SUCCESS
//        } ?: run {
//            Log.d("NULL:", "objeto retornou nulo")
//        }
//        this.baseData.value = baseK
//    }
//
//    private fun error(e: Exception) {
//        this.baseData.value = BaseK(status = BaseK.STATUS.ERROR)
//    }
}