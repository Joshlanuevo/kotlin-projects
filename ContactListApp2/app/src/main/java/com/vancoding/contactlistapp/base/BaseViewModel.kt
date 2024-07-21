package com.vancoding.contactlistapp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import androidx.lifecycle.viewModelScope
import com.vancoding.contactlistapp.bean.UsersBean
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

/**
 * Author:yogi
 * Date:2020-10-6
 * Time:15:47
 */

abstract class BaseViewModel : ViewModel() {
    val SUCCESS = 200

    sealed class LoadState<T>(
        val hasMore: Boolean = false,
        val isRefresh: Boolean = false,
        val isPull2Refresh: Boolean = false,
        val exc: Throwable? = null,
        val data: T? = null,
        val dataOld: T? = null,
    ) {
        class Loading<T>(isRefresh: Boolean = false, isPull2Refresh: Boolean = false, data: T? = null) :
            LoadState<T>(isRefresh = isRefresh, isPull2Refresh = isPull2Refresh, data = data)

        class Fail<T>(exc: Throwable? = null, isRefresh: Boolean = false, dataOld: T? = null) :
            LoadState<T>(exc = exc, isRefresh = isRefresh, dataOld = dataOld)

        class Success<T>(
            data: T? = null,
            dataOld: T? = null,
            isRefresh: Boolean = false,
            isPull2Refresh: Boolean = false,
            hasMore: Boolean = true,
        ) : LoadState<T>(
            data = data,
            dataOld = dataOld,
            isRefresh = isRefresh,
            isPull2Refresh = isPull2Refresh,
            hasMore = hasMore
        )
    }

    fun requestLaunch(
        block: suspend CoroutineScope.() -> Unit,
        onError: ((Throwable) -> Unit)? = null,
        onStart: (() -> Unit)? = null,
        onFinally: (() -> Unit)? = null,
    ): Job {
        return viewModelScope.launch {
            try {
                onStart?.invoke()
                block()
            } catch (e: Throwable) {
                onError?.invoke(e)
            } finally {
                onFinally?.invoke()
            }
        }
    }
}