package com.zg.burgerjoint.activities

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.transition.Fade
import android.transition.Slide
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.recyclerview.widget.GridLayoutManager
import com.zg.burgerjoint.R
import com.zg.burgerjoint.adapters.CartAdapter
import com.zg.burgerjoint.data.vos.BurgerVO
import com.zg.burgerjoint.mvp.presenters.CartPresenter
import com.zg.burgerjoint.mvp.presenters.impls.CartPresenterImpl
import com.zg.burgerjoint.mvp.views.CartView
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_main.toolbar

class CartActivity : BaseActivity(), CartView {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, CartActivity::class.java)
        }
    }

    private lateinit var mBurgerAdapter: CartAdapter
    private lateinit var mPresenter: CartPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpAnimations()
        setContentView(R.layout.activity_cart)
        setSupportActionBar(toolbar)
        setUpPresenter()
        setUpListeners()
        setUpRecycler()
        mPresenter.onUIReady(this)
    }

    private fun setUpAnimations(){
        with(window){
            requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
            val slide = Slide()
            slide.slideEdge = Gravity.RIGHT
            slide.interpolator = AccelerateDecelerateInterpolator()
            slide.duration = 500

            enterTransition = slide
            exitTransition = slide
        }
    }

    private fun setUpListeners() {
        btnCheckOut.setOnClickListener { mPresenter.onTapCheckout() }
        ivCancel.setOnClickListener { mPresenter.onTapCancelThankYouMessage() }
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<CartPresenterImpl, CartView>()
    }

    private fun setUpRecycler() {
        mBurgerAdapter = CartAdapter(mPresenter)
        rvCart.adapter = mBurgerAdapter
        rvCart.layoutManager = GridLayoutManager(applicationContext, 1)
    }

    override fun displayItemsInCart(burgers: List<BurgerVO>) {
        mBurgerAdapter.setNewData(burgers)
    }

    override fun displayThankYouMessage() {
        rlThankYouMessage.visibility = View.VISIBLE

        val animator = ObjectAnimator.ofFloat(rlThankYouMessage,
            View.TRANSLATION_Y, rlThankYouMessage.height.toFloat(), 0f)
        animator.duration = 500
        animator.start()
    }

    override fun hideThankYouMessage() {
        val animator = ObjectAnimator.ofFloat(rlThankYouMessage,
            View.TRANSLATION_Y, 0f, rlThankYouMessage.height.toFloat())
        animator.duration = 500
        animator.start()
    }
}