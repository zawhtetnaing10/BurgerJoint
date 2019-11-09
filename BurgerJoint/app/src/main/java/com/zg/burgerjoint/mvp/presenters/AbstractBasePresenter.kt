package com.zg.burgerjoint.mvp.presenters

import androidx.lifecycle.ViewModel
import com.maex.shared.mvp.presenters.BasePresenter
import com.zg.burgerjoint.mvp.views.BaseView

abstract class AbstractBasePresenter<T: BaseView> : BasePresenter<T>, ViewModel() {

    protected lateinit var mView : T

    override fun initPresenter(view: T){
        mView = view
    }
}