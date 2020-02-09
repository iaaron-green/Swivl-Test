package com.example.swivltestassigment.data.datasource.remote

import com.example.swivltestassigment.data.network.RestClient

class UserApiDataSource : BaseDataSource() {
    suspend fun getUsersList(pageSize: Int) =
        getResult { RestClient().getUsersList(pageSize).execute() }

    suspend fun getUser(login: String) =
        getResult { RestClient().getUser(login).execute() }
}