package com.example.swivltestassigment.ui.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.swivltestassigment.data.datasource.remote.UserApiDataSource
import com.example.swivltestassigment.data.repository.UserRepository
import com.example.swivltestassigment.ui.fragment.home.HomeViewModel
import com.example.swivltestassigment.ui.fragment.profile.ProfileViewModel

class ViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(UserRepository(UserApiDataSource())) as T
        }
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)){
            return ProfileViewModel(UserRepository(UserApiDataSource())) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}