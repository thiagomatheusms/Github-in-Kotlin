package com.example.githubKotlin.feature.repositoryK

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.githubKotlin.endpoints.GetDataService
import com.example.githubKotlin.model.BaseK
import com.example.githubKotlin.model.RepositoryK
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RepositoryKDataSource(private val getDataService: GetDataService) :
    PageKeyedDataSource<Int, RepositoryK>(), CoroutineScope {

    private val job = Job()
    override val coroutineContext = job + Dispatchers.IO

    private var base: MutableLiveData<BaseK> = MutableLiveData()

    companion object {
        const val FIRST_PAGE = 1
    }

    init {
        base.postValue(BaseK(status = BaseK.STATUS.LOADING))
    }


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, RepositoryK>
    ) {

        base.postValue(BaseK(status = BaseK.STATUS.LOADING))

        CoroutineScope(coroutineContext).launch {
            try {

                val response = getDataService.getBaseResponse(FIRST_PAGE)

                when {
                    response.isSuccessful -> {
                        response.body()?.let {
                            it.status = BaseK.STATUS.SUCCESS
                            base.postValue(it)
                            getBasek()
                            callback.onResult(it.items, null, FIRST_PAGE + 1)
                        }
                    }
                    else -> null
                }
            } catch (exception: Exception) {
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, RepositoryK>) {

        base.postValue(BaseK(status = BaseK.STATUS.LOADING))

        CoroutineScope(coroutineContext).launch {
            try {

                val response = getDataService.getBaseResponse(params.key)

                when {
                    response.isSuccessful -> {
                        var key = params.key + 1
                        response.body()?.let {
                            it.status = BaseK.STATUS.SUCCESS
                            base.postValue(it)
                            Log.i("BASE", base.value?.total_count.toString())
                            getBasek()
                            callback.onResult(it.items, key)
                        }
                    }
                    else -> null
                }
            } catch (exception: Exception) {
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, RepositoryK>) {

    }

    fun getBasek() = base

}