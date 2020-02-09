package com.example.swivltestassigment.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.swivltestassigment.data.datasource.paging.PageDataSourceFactory
import com.example.swivltestassigment.data.datasource.remote.UserApiDataSource
import com.example.swivltestassigment.data.model.UserModel
import com.example.swivltestassigment.data.model.UserProfileModel
import com.example.swivltestassigment.data.network.Result
import com.example.swivltestassigment.data.network.responce.UserProfileApi
import kotlinx.coroutines.Dispatchers

class UserRepository(
    private val userApiDataSource: UserApiDataSource
) {

    fun observePagedSets(connectivityAvailable: Boolean) =
        observeRemotePagedSets()

    private fun observeRemotePagedSets()
            : LiveData<PagedList<UserModel>> {
        val dataSourceFactory = PageDataSourceFactory(userApiDataSource)
        return LivePagedListBuilder(
            dataSourceFactory,
            PageDataSourceFactory.pagedListConfig()
        ).build()
    }

    fun observeUser(login: String): LiveData<Result<UserProfileApi>> {
        return liveData(Dispatchers.IO) {
            var result = userApiDataSource.getUser(login)
            emit(result)
        }
    }
}