package com.zg.burgerjoint.delegates

import android.widget.ImageView
import com.zg.burgerjoint.data.vos.BurgerVO

interface BurgerViewHolderActionDelegate {
    fun onTapBurger(burger : BurgerVO)
    fun onTapAddToCart(burger : BurgerVO, burgerImageView: ImageView)
}