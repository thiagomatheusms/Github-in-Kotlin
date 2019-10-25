package com.example.githubKotlin.model

import com.google.gson.annotations.SerializedName

class RepositoryK(
    @SerializedName("name")
    var name: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("owner")
    var owner: OwnerK,
    @SerializedName("stargazers_count")
    var stargazers_count: Float,
    @SerializedName("forks_count")
    var forks_count: Int
)
