package com.example.swivltestassigment.ui.fragment.profile

import androidx.lifecycle.ViewModel
import com.example.swivltestassigment.data.repository.UserRepository

class ProfileViewModel(repository: UserRepository): ViewModel() {
    lateinit var login: String;
    val user by lazy { repository.observeUser(login) }
}