package com.example.swivltestassigment.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.swivltestassigment.R
import com.example.swivltestassigment.data.model.UserModel
import com.example.swivltestassigment.databinding.FragmentHomeBinding
import com.example.swivltestassigment.extension.injectViewModel
import com.example.swivltestassigment.ui.activity.MainActivity
import com.example.swivltestassigment.ui.adapter.HomeAdapter
import com.example.swivltestassigment.ui.fragment.BaseFragment
import com.example.swivltestassigment.ui.fragment.ViewModelFactory
import com.example.swivltestassigment.ui.fragment.profile.ProfileFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(), HomeAdapter.onItemClickListener {
    lateinit var binding: FragmentHomeBinding;
    lateinit var adapter: HomeAdapter;
    var pagedList: PagedList<UserModel>? = null
    var viewModelFactory: ViewModelFactory = ViewModelFactory()

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        adapter = HomeAdapter(this);
        viewModel = injectViewModel(viewModelFactory)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUI()
        subscribeUI()
    }

    private fun initUI() {
        (getBaseActivity() as MainActivity).supportActionBar?.title = getString(R.string.home_title)
        recyclerView.layoutManager = LinearLayoutManager(context, VERTICAL, false)
        recyclerView.adapter = adapter
        binding.swipeRefreshLayout.setOnRefreshListener {
            pagedList?.dataSource?.invalidate()
        }
    }

    private fun subscribeUI() {
        viewModel.userPages.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            pagedList = it
            binding.swipeRefreshLayout.isRefreshing = false;
        });
    }

    override fun onItemClick(userModel: UserModel) {
        val profile = ProfileFragment.newInstance(userModel)
        getBaseActivity()?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.main_container, profile)?.addToBackStack(null)?.commit();
    }

}
