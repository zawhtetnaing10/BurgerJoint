package com.zg.burgerjoint.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.bumptech.glide.Glide
import com.zg.burgerjoint.R
import com.zg.burgerjoint.data.vos.BurgerVO
import com.zg.burgerjoint.mvp.presenters.BurgerDetailsPresenter
import com.zg.burgerjoint.mvp.presenters.impls.BurgerDetailsPresenterImpl
import com.zg.burgerjoint.mvp.views.BurgerDetailsView
import kotlinx.android.synthetic.main.activity_burger_details.*
import kotlinx.android.synthetic.main.view_item_burger.view.*

class BurgerDetailsActivity : BaseActivity(),BurgerDetailsView {

    companion object{

        private const val EXTRA_BURGER_ID = "Burger Id Extra"

        fun newIntent(context: Context, burgerId : Int): Intent{
            val intent = Intent(context, BurgerDetailsActivity::class.java)
            intent.putExtra(EXTRA_BURGER_ID,burgerId)
            return intent
        }
    }

    private lateinit var mPresenter : BurgerDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_burger_details)
        setUpPresenter()
        setUpListeners()
        val burgerId = intent.getIntExtra(EXTRA_BURGER_ID, 0)

        mPresenter.onBurgerDetailsUiReady(this, burgerId)
    }

    private fun setUpListeners(){
        ivBurger.setOnClickListener {
           val animation =  AnimationUtils.loadAnimation(applicationContext, R.anim.rotate)
            ivBurger.startAnimation(animation)
        }
    }

    private fun setUpPresenter(){
        mPresenter = getPresenter<BurgerDetailsPresenterImpl,BurgerDetailsView>()
    }

    override fun displayBurgerDetails(burger : BurgerVO) {
        tvBurgerName.text = burger.burgerName
        tvDescription.text = burger.burgerDescription
        Glide.with(ivBurger)
            .load(burger.burgerImageUrl)
            .into(ivBurger)
    }

}