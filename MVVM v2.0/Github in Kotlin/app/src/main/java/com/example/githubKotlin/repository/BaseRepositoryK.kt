package com.example.githubKotlin.repository

import com.example.githubKotlin.endpoints.GetDataService
import com.example.githubKotlin.model.BaseK

class BaseRepositoryK(private val getDataService: GetDataService) : GithubRepository {

    override suspend fun getBase(success: (BaseK?) -> Unit, error: (Exception) -> Unit) {
       getDataService.getBaseResponse(1).let{
           success(it.body())
       }

    }

}