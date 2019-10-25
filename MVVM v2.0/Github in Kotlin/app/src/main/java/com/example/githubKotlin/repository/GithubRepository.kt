package com.example.githubKotlin.repository

import com.example.githubKotlin.model.BaseK

interface GithubRepository {

    suspend fun getBase(success: (BaseK?) -> Unit, error: (Exception) -> Unit)
}