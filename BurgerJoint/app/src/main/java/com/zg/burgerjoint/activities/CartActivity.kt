package com.zg.burgerjoint.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
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
        setContentView(R.layout.activity_cart)
        setSupportActionBar(toolbar)
        setUpPresenter()
        setUpListeners()
        setUpRecycler()
        mPresenter.onUIReady(this)
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
    }

    override fun hideThankYouMessage() {
        rlThankYouMessage.visibility = View.GONE
    }
}