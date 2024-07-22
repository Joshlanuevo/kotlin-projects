package com.vancoding.contactlistapp.ui

import com.bumptech.glide.Glide
import com.dylanc.viewbinding.binding
import com.vancoding.contactlistapp.base.BaseActivity
import com.vancoding.contactlistapp.databinding.ActivityUserInfoBinding

class UserInfoActivity : BaseActivity() {
    private val bindView: ActivityUserInfoBinding by binding()

    override fun initView() {
        bindView.toolbar.run {
            viewBack.setOnClickListener {
                finish()
            }
            tvTitle.text = "User Info"
        }

        val email = intent.getStringExtra("email")
        val firstName = intent.getStringExtra("first_name")
        val lastName = intent.getStringExtra("last_name")
        val avatar = intent.getStringExtra("avatar")

        bindView.userInfoEmail.text = email
        bindView.userInfoName.text = "$firstName $lastName"
        Glide.with(this).load(avatar).circleCrop().into(bindView.userInfoAvatar)
    }

    override fun requestData() {}

    override fun observeCallBack() {}

}