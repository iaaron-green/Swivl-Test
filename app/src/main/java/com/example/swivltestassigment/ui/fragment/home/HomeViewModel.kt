package com.example.swivltestassigment.ui.fragment.home

import androidx.lifecycle.ViewModel
import com.example.swivltestassigment.data.repository.UserRepository

class HomeViewModel(private val userRepository: UserRepository)
    : ViewModel() {
    var connectivityAvailable: Boolean = false

    val userPages by lazy {
        userRepository.observePagedSets(connectivityAvailable)
    }
}
