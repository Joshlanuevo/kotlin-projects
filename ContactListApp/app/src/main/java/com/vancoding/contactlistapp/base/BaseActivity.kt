package com.vancoding.contactlistapp.base

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity


abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        if (isTranslucentStatus()) {
            setTranslucentStatus();
        }

        initView();
        requestData();
        observeCallBack();
    }

    /**
     * For status bar transparency
     */
    open fun setTranslucentStatus() {
        val window = window;
        val decorView = window.decorView;
        decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        decorView.systemUiVisibility =
            decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        window.statusBarColor = Color.TRANSPARENT;
    }

    abstract fun initView();

    abstract fun requestData();

    abstract fun observeCallBack();

    open fun isTranslucentStatus(): Boolean {
        return true;
    }
}