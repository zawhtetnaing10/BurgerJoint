package com.zg.burgerjoint.activities

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import com.zg.burgerjoint.R
import com.zg.burgerjoint.adapters.BurgerAdapter
import com.zg.burgerjoint.data.vos.BurgerVO
import com.zg.burgerjoint.mvp.presenters.MainPresenter
import com.zg.burgerjoint.mvp.presenters.impls.MainPresenterImpl
import com.zg.burgerjoint.mvp.views.MainView

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(),MainView {

    private lateinit var mBurgerAdapter: BurgerAdapter
    private lateinit var mPresenter : MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setUpPresenter()
        setUpListeners()
        setUpRecycler()
        mPresenter.onUIReady(this)
    }

    private fun setUpListeners(){
        ivCart.setOnClickListener {
            mPresenter.onTapCart()
        }
    }

    private fun setUpPresenter(){
        mPresenter = getPresenter<MainPresenterImpl, MainView>()
    }

    private fun setUpRecycler() {
        mBurgerAdapter = BurgerAdapter(mPresenter)
        rvBurgerList.adapter = mBurgerAdapter
        rvBurgerList.layoutManager = GridLayoutManager(applicationContext, 1)
    }

    override fun navigateToBurgerDetailsScreen(burgerId: Int) {
        startActivity(BurgerDetailsActivity.newIntent(this, burgerId))
    }

    override fun navigatetoCartScreen() {
        startActivity(CartActivity.newIntent(this))
    }

    override fun displayBurgerList(burgerList: List<BurgerVO>) {
        mBurgerAdapter.setNewData(burgerList)
    }

    override fun displayCountInCart(burgersInCartCount: Int) {
        tvCartCount.text = burgersInCartCount.toString()
    }

    override fun addBurgerToCart(burger: BurgerVO) {
        //Play Animation
    }
}
