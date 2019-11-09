package com.zg.burgerjoint.mvp.views

import android.widget.ImageView
import com.zg.burgerjoint.data.vos.BurgerVO

interface MainView : BaseView{
    fun displayBurgerList(burgerList : List<BurgerVO>)
    fun navigateToBurgerDetailsScreenWithAnimation(burgerId : Int, burgerImageView: ImageView)
    fun navigateToCartScreen()
    fun animateAddBurgerToCart(burger : BurgerVO, burgerImageView : ImageView)
    fun displayCountInCart(burgersInCartCount : Int)

}