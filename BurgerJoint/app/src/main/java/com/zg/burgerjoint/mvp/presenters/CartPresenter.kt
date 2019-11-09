package com.zg.burgerjoint.mvp.presenters

import com.maex.shared.mvp.presenters.BasePresenter
import com.zg.burgerjoint.delegates.CartViewHolderActionDelegate
import com.zg.burgerjoint.mvp.views.CartView

interface CartPresenter : BasePresenter<CartView>, CartViewHolderActionDelegate {
    fun onTapCheckout()
    fun onTapCancelThankYouMessage()
}