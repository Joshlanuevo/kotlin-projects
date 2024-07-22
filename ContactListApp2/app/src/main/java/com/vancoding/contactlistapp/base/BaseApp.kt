package com.vancoding.contactlistapp.base

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApp : Application() {
    companion object {
        var appContent: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        initData()
    }

    private fun initData() {
        appContent = applicationContext
    }
}