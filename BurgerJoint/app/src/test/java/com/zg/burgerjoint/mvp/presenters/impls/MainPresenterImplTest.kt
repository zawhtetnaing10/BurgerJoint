package com.zg.burgerjoint.mvp.presenters.impls

import android.widget.ImageView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.zg.burgerjoint.data.model.BurgerModel
import com.zg.burgerjoint.data.model.impls.BurgerModelImpl
import com.zg.burgerjoint.data.model.impls.MockBurgerModelImpl
import com.zg.burgerjoint.data.vos.BurgerVO
import com.zg.burgerjoint.dummy.getDummyBurgers
import com.zg.burgerjoint.mvp.views.MainView
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
class MainPresenterImplTest {

    private lateinit var mPresenter: MainPresenterImpl

    @RelaxedMockK
    private lateinit var mView: MainView

    private lateinit var mBurgerModel: BurgerModel

    @Before
    fun setUpPresenter() {
        MockKAnnotations.init(this)

        BurgerModelImpl.init(ApplicationProvider.getApplicationContext())
        mBurgerModel = MockBurgerModelImpl

        mPresenter = MainPresenterImpl()
        mPresenter.initPresenter(mView)
        mPresenter.mBurgerModel = this.mBurgerModel
    }



    companion object {

        fun getMockLifeCycleOwner() : LifecycleOwner{
            val lifeCycleOwner = mock(LifecycleOwner::class.java)
            val lifeCycleRegistry = LifecycleRegistry(lifeCycleOwner)
            lifeCycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
            `when`(lifeCycleOwner.lifecycle).thenReturn(lifeCycleRegistry)
            return lifeCycleOwner
        }

        fun getDummyBurger(): BurgerVO {
            val tappedBurger = BurgerVO()
            tappedBurger.burgerId = 1
            tappedBurger.burgerName = "Big Mac"
            tappedBurger.burgerImageUrl = ""
            tappedBurger.burgerDescription = "Big Mac Burger"
            return tappedBurger
        }

        fun getDummyImageView(): ImageView {
            return ImageView(ApplicationProvider.getApplicationContext())
        }
    }


//    @Test
//    fun onTapAddToCart_callAddBurgerToCart() {
//        mPresenter.onTapAddToCart(getDummyBurger(), getDummyImageView())
//        verify {
//            mView.addBurgerToCart(getDummyBurger(), getDummyImageView())
//        }
//    }
//
//    @Test
//    fun onTapCart_callNavigateToCartScreen() {
//        mPresenter.onTapCart()
//        verify {
//            mView.navigatetoCartScreen()
//        }
//    }
//
//    @Test
//    fun onTapBurger_callNavigateToBurgerDetails() {
//
//        mPresenter.onTapBurger(getDummyBurger(), getDummyImageView())
//        verify {
//            mView.navigateToBurgerDetailsScreen(getDummyBurger().burgerId, getDummyImageView())
//        }
//    }
//
//    @Test
//    fun onUIReady_callDisplayBurgerList_callDisplayCountInCart() {
//        mPresenter.onUIReady(getMockLifeCycleOwner())
//        verify {
//            mView.displayBurgerList(getDummyBurgers())
//            mView.displayCountInCart(0)
//        }
//    }
}