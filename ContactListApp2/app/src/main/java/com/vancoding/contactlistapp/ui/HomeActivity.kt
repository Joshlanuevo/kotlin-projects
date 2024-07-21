package com.vancoding.contactlistapp.ui


import androidx.lifecycle.ViewModelProvider
import com.dylanc.viewbinding.binding
import com.vancoding.contactlistapp.api.RetrofitInstance
import com.vancoding.contactlistapp.base.BaseActivity
import com.vancoding.contactlistapp.base.BaseViewModel
import com.vancoding.contactlistapp.databinding.ActivityHomeBinding
import com.vancoding.contactlistapp.repository.UsersRepository
import com.vancoding.contactlistapp.viewmodel.UsersViewModel
import com.vancoding.contactlistapp.viewmodel.UsersViewModelFactory


class HomeActivity : BaseActivity() {
    private val bindView: ActivityHomeBinding by binding()

    private lateinit var mviewModel: UsersViewModel
    override fun initView() {
        val retrofit = RetrofitInstance.service
        val mRepository = UsersRepository(retrofit)
        mviewModel = ViewModelProvider(this, UsersViewModelFactory(mRepository)) [UsersViewModel::class.java]
    }

    override fun requestData() {
        mviewModel.getUsersList()
    }

    override fun observeCallBack() {
        mviewModel.usersLiveData.observe(this) { loadState ->
            when (loadState) {
                is BaseViewModel.LoadState.Success -> {
                    bindView.textView.text = loadState.data?.size.toString()
                }
                is BaseViewModel.LoadState.Fail -> {
                    // Handle error state if needed
                }
                is BaseViewModel.LoadState.Loading -> {
                    // Handle loading state if needed
                }
            }
        }
    }
}