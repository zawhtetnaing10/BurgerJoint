package com.zg.burgerjoint.mvp.views

import com.zg.burgerjoint.data.vos.BurgerVO

interface BurgerDetailsView : BaseView {
    fun displayBurgerDetails(burger : BurgerVO)
}