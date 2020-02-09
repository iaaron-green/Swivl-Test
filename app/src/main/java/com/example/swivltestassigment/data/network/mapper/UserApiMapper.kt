package com.example.swivltestassigment.data.network.mapper

import com.example.swivltestassigment.data.model.UserModel
import com.example.swivltestassigment.data.network.responce.UserApi

class UserApiMapper: ApiMapper<UserApi, UserModel>() {
    override fun fromEntity(t: UserApi): UserModel {
        return UserModel(t.id, t.login, t.avatar_url);
    }

    override fun listFromEntity(t: List<UserApi>?): List<UserModel> {
        var list: ArrayList<UserModel> = ArrayList()
        if (t != null) {
            for (userApi in t) {
                list.add(UserModel(userApi.id, userApi.login, userApi.avatar_url));
            }
        }
        return list;
    }
}