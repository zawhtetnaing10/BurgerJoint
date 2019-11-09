package com.zg.burgerjoint.mvp.views

import com.zg.burgerjoint.data.vos.BurgerVO

interface MainView : BaseView{
    fun displayBurgerList(burgerList : List<BurgerVO>)
    fun navigateToBurgerDetailsScreen(burgerId : Int)
    fun navigatetoCartScreen()
    fun addBurgerToCart(burger : BurgerVO)
    fun displayCountInCart(burgersInCartCount : Int)

}