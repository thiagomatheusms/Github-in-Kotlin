package com.example.githubKotlin.model

import com.google.gson.annotations.SerializedName

class OwnerK(
    @SerializedName("login")
    var login: String,
    @SerializedName("avatar_url")
    var avatar_url: String
)