package com.example.githubKotlin.endpoints

import com.example.githubKotlin.model.BaseK
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetDataService {

    @GET("search/repositories?q=language:Java&sort=stars")
    suspend fun getBaseResponse(@Query("page") page: Int): Response<BaseK>

//    fun getPullResponse() : Call<List<>>
}