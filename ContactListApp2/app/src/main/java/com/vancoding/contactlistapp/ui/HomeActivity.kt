package com.vancoding.contactlistapp.ui

import com.vancoding.contactlistapp.R
import com.dylanc.viewbinding.binding
import com.vancoding.contactlistapp.base.BaseActivity
import com.vancoding.contactlistapp.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity() {
    private val bindView: ActivityHomeBinding by binding()
    override fun initView() {
        bindView.textView.text = "Hello World"
    }

    override fun requestData() {}

    override fun observeCallBack() {}
}