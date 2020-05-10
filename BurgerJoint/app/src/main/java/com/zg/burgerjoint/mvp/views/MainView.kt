package com.zg.burgerjoint.mvp.views

import android.widget.ImageView
import com.zg.burgerjoint.data.vos.BurgerVO

interface MainView : BaseView{
    fun displayBurgerList(burgerList : List<BurgerVO>)
    fun navigateToBurgerDetailsScreen(burgerId : Int, burgerImageView : ImageView)
    fun navigatetoCartScreen()
    fun addBurgerToCart(burger : BurgerVO, burgerImageView : ImageView)
    fun displayCountInCart(burgersInCartCount : Int)

}