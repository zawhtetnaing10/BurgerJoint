package com.zg.burgerjoint.mvp.presenters.impls

import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.zg.burgerjoint.data.model.BurgerModel
import com.zg.burgerjoint.data.model.impls.BurgerModelImpl
import com.zg.burgerjoint.data.vos.BurgerVO
import com.zg.burgerjoint.mvp.presenters.AbstractBasePresenter
import com.zg.burgerjoint.mvp.presenters.MainPresenter
import com.zg.burgerjoint.mvp.views.MainView

class MainPresenterImpl : MainPresenter, BaseAppPresenterImpl<MainView>() {

    var mBurgerModel : BurgerModel = BurgerModelImpl

    override fun onTapAddToCart(burger: BurgerVO, burgerImageView : ImageView) {
        mBurgerModel.addItemToCart(burger)
        mView.addBurgerToCart(burger, burgerImageView)
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

    override fun onTapBurger(burger: BurgerVO, burgerImageView: ImageView) {
        mView.navigateToBurgerDetailsScreen(burgerId = burger.burgerId, burgerImageView = burgerImageView)
    }
}