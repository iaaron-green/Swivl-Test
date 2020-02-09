package com.example.swivltestassigment.data.model

data class UserProfileModel(
    var name: String,
    var photoUrl: String,
    var blog: String,
    var followers: Int,
    var gists: Int,
    var repos: Int
)