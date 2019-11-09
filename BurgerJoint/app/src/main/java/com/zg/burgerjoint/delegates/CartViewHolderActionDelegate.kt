package com.zg.burgerjoint.delegates

import com.zg.burgerjoint.data.vos.BurgerVO

interface CartViewHolderActionDelegate {
    fun onTapRemoveFromCart(burger : BurgerVO)
}