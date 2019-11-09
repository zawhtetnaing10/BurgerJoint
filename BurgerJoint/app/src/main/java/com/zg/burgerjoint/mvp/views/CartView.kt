package com.zg.burgerjoint.mvp.views

import com.zg.burgerjoint.data.vos.BurgerVO

interface CartView : BaseView {
    fun displayItemsInCart(burgers : List<BurgerVO>)
    fun displayThankYouMessage()
    fun hideThankYouMessage()
}