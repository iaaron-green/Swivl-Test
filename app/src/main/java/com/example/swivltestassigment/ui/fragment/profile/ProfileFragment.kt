package com.example.swivltestassigment.ui.fragment.profile


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.swivltestassigment.Constants.Companion.BUNDLE_KEY
import com.example.swivltestassigment.data.model.UserModel
import com.example.swivltestassigment.data.model.UserProfileModel
import com.example.swivltestassigment.data.network.mapper.UserProfileMapper
import com.example.swivltestassigment.databinding.FragmentProfileBinding
import com.example.swivltestassigment.ui.fragment.ViewModelFactory
import com.example.swivltestassigment.extension.injectViewModel
import com.example.swivltestassigment.data.network.Result
import com.example.swivltestassigment.ui.activity.MainActivity
import com.example.swivltestassigment.ui.fragment.BaseFragment
import com.google.gson.Gson
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment private constructor(): BaseFragment() {
    lateinit var binding: FragmentProfileBinding;
    private lateinit var viewModel: ProfileViewModel;
    private var viewModelFactory: ViewModelFactory = ViewModelFactory();

    companion object {
        fun newInstance(userModel: UserModel) =
            ProfileFragment().apply {
                arguments = bundleOf(BUNDLE_KEY to Gson().toJson(userModel))
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var data = Gson().fromJson(arguments?.getString(BUNDLE_KEY), UserModel::class.java)
        viewModel = injectViewModel(viewModelFactory)
        viewModel.login = data.login

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        initUI()
        subscibeUI()

        return binding.root
    }

    private fun initUI() {
        showProgressBar()

    }

    private fun subscibeUI() {
        viewModel.user.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Result.Status.SUCCESS -> {
                    it.data?.let {
                        updateUI(UserProfileMapper().fromEntity(it))
                    }
                    hideProgressBar()
                }
                Result.Status.LOADING -> {

                }
                Result.Status.ERROR -> {
                    hideProgressBar()
                    showToastLong(it.message)
                }
            }
        })
    }

    private fun updateUI(userProfile: UserProfileModel) {
        (getBaseActivity() as MainActivity).supportActionBar?.title = userProfile.name
        binding.model = userProfile
        Picasso.get().load(userProfile.photoUrl).into(binding.ciUserProfilePhoto)
    }


}
