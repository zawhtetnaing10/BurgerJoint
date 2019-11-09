package com.maex.shared.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.zg.burgerjoint.mvp.views.BaseView

interface BasePresenter<V: BaseView> {
    fun onUIReady(owner: LifecycleOwner)
    fun initPresenter(view: V)
}