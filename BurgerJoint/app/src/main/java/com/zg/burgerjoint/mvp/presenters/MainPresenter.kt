package com.zg.burgerjoint.mvp.presenters

import com.maex.shared.mvp.presenters.BasePresenter
import com.zg.burgerjoint.delegates.BurgerViewHolderActionDelegate
import com.zg.burgerjoint.mvp.views.MainView

interface MainPresenter :  BasePresenter<MainView>,BurgerViewHolderActionDelegate{
    fun onTapCart()
}