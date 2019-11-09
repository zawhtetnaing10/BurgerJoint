package com.zg.burgerjoint.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.zg.burgerjoint.data.model.BurgerModel
import com.zg.burgerjoint.data.model.impls.BurgerModelImpl
import com.zg.burgerjoint.data.vos.BurgerVO
import com.zg.burgerjoint.mvp.presenters.AbstractBasePresenter
import com.zg.burgerjoint.mvp.presenters.MainPresenter
import com.zg.burgerjoint.mvp.views.MainView

class MainPresenterImpl : MainPresenter, BaseAppPresenterImpl<MainView>() {

    private val mBurgerModel = BurgerModelImpl

    override fun onTapAddToCart(burger: BurgerVO) {
        mBurgerModel.addItemToCart(burger)
    }

    override fun onTapCart() {
        mView.navigatetoCartScreen()
    }

    override fun onUIReady(owner: LifecycleOwner) {
        mBurgerModel.getAllBurgers()
            .observe(owner, Observer {
                mView.displayBurgerList(it)
            })

        mBurgerModel.getBurgersInCart()
            .observe(owner, Observer {
                mView.displayCountInCart(it.count())
            })
    }

    override fun onTapBurger(burger: BurgerVO) {
        mView.navigateToBurgerDetailsScreen(burgerId = burger.burgerId)
    }
}