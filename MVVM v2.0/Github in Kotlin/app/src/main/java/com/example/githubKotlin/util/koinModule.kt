package com.example.githubKotlin.util

import com.example.githubKotlin.endpoints.GetDataService
import com.example.githubKotlin.feature.repositoryK.RepositoryKAdapter
import com.example.githubKotlin.feature.repositoryK.RepositoryKDataSource
import com.example.githubKotlin.feature.repositoryK.RepositoryKViewModel
import com.example.githubKotlin.repository.BaseRepositoryK
import com.example.githubKotlin.repository.GithubRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val githubModule = module {

    /*Dependências RepositoryActivity*/

    factory { RepositoryKAdapter() }

    viewModel { RepositoryKViewModel(get()) }

    /*Dependências RepositoryKViewModel*/

    factory {
        RepositoryKDataSource(get())
    }

    /*Dependências BaseRepositoryK e RepositoryKDataSource*/
    factory<GetDataService> {
        get<Retrofit>().create(GetDataService::class.java)
    }

    factory<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

//    factory<GithubRepository> {
//        BaseRepositoryK(get())
//    }


}