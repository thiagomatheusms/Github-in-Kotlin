package com.example.githubKotlin.model

import com.google.gson.annotations.SerializedName

data class BaseK(
    @SerializedName("total_count")
    val total_count: Int = 0,
    @SerializedName("items")
    val items: ArrayList<RepositoryK> = ArrayList(),
    var status: STATUS
){
    enum class STATUS{
        LOADING,
        SUCCESS,
        ERROR
    }
}