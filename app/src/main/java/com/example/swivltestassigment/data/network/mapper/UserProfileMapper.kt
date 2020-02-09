package com.example.swivltestassigment.data.network.mapper

import com.example.swivltestassigment.data.model.UserProfileModel
import com.example.swivltestassigment.data.network.responce.UserProfileApi

class UserProfileMapper: ApiMapper<UserProfileApi, UserProfileModel>() {
    override fun fromEntity(t: UserProfileApi): UserProfileModel {
        return UserProfileModel(t.name, t.avatar_url, t.blog, t.followers, t.public_gists, t.public_repos)
    }

    override fun listFromEntity(t: List<UserProfileApi>?): List<UserProfileModel> {
        return emptyList()
    }

}