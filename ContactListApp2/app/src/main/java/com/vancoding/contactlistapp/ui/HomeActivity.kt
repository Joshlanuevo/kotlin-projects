package com.vancoding.contactlistapp.ui

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dylanc.viewbinding.binding
import com.vancoding.contactlistapp.adapter.UsersAdapter
import com.vancoding.contactlistapp.api.RetrofitInstance
import com.vancoding.contactlistapp.base.BaseActivity
import com.vancoding.contactlistapp.base.BaseViewModel
import com.vancoding.contactlistapp.databinding.ActivityHomeBinding
import com.vancoding.contactlistapp.db.UsersDb
import com.vancoding.contactlistapp.repository.UsersRepository
import com.vancoding.contactlistapp.viewmodel.UsersViewModel
import com.vancoding.contactlistapp.viewmodel.UsersViewModelFactory


class HomeActivity : BaseActivity() {
    private val bindView: ActivityHomeBinding by binding()
    private lateinit var mviewModel: UsersViewModel
    private lateinit var mAdapter: UsersAdapter

    override fun initView() {
        bindView.swipeLayout.setOnRefreshListener { requestData() }

        val localDb =  UsersDb.getInstance(applicationContext)
        val retrofit = RetrofitInstance.service
        val mRepository = UsersRepository(retrofit, localDb.usersDao())
        mviewModel = ViewModelProvider(this, UsersViewModelFactory(mRepository)) [UsersViewModel::class.java]

        mAdapter = UsersAdapter(emptyList())
        bindView.listUsers.layoutManager = LinearLayoutManager(this)
        bindView.listUsers.adapter = mAdapter
    }

    override fun requestData() {
        mviewModel.getUsersList()
    }

    override fun observeCallBack() {
        mviewModel.usersLiveData.observe(this) { userListData ->
            when (userListData) {
                is BaseViewModel.LoadState.Success -> {
                    bindView.listUsers.adapter = userListData.data?.let { UsersAdapter(it) }
                    bindView.swipeLayout.isRefreshing = false
                }
                is BaseViewModel.LoadState.Fail -> {}
                is BaseViewModel.LoadState.Loading -> {}
            }
        }
    }
}