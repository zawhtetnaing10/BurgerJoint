package com.zg.burgerjoint.activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.transition.Fade
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.zg.burgerjoint.R
import com.zg.burgerjoint.adapters.BurgerAdapter
import com.zg.burgerjoint.data.vos.BurgerVO
import com.zg.burgerjoint.mvp.presenters.MainPresenter
import com.zg.burgerjoint.mvp.presenters.impls.MainPresenterImpl
import com.zg.burgerjoint.mvp.views.MainView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_item_burger.*

class MainActivity : BaseActivity(), MainView {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    private lateinit var mBurgerAdapter: BurgerAdapter
    private lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpTransitions()
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setUpPresenter()
        setUpListeners()
        setUpRecycler()
        mPresenter.onUIReady(this)
    }

    private fun setUpTransitions() {
        with(window) {
            requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
            val fade = Fade()
            fade.duration = 500
            fade.interpolator = AccelerateDecelerateInterpolator()
            exitTransition = fade
        }
    }

    private fun setUpListeners() {
        ivCart.setOnClickListener {
            mPresenter.onTapCart()
        }

        btnPlayGame.setOnClickListener {
            startActivity(GameActivity.newIntent(this))
        }
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<MainPresenterImpl, MainView>()
    }

    private fun setUpRecycler() {
        mBurgerAdapter = BurgerAdapter(mPresenter)
        rvBurgerList.adapter = mBurgerAdapter
        rvBurgerList.layoutManager = GridLayoutManager(applicationContext, 1)
    }

    override fun navigateToBurgerDetailsScreen(burgerId: Int, burgerImageView: ImageView) {
        val pair = Pair
            .create(burgerImageView as View, "tBurgerImage")
        val namePair = Pair
            .create(tvBurgerName as View, "tBurgerName")
        val options = ActivityOptionsCompat
            .makeSceneTransitionAnimation(this, pair, namePair)
        startActivity(
            BurgerDetailsActivity.newIntent(this, burgerId)
            , options.toBundle()
        )
    }

    override fun navigatetoCartScreen() {
        startActivity(
            CartActivity.newIntent(this),
            ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle()
        )
    }

    override fun displayBurgerList(burgerList: List<BurgerVO>) {
        mBurgerAdapter.setNewData(burgerList)
    }

    override fun displayCountInCart(burgersInCartCount: Int) {
        tvCartCount.text = burgersInCartCount.toString()
    }

    override fun addBurgerToCart(burger: BurgerVO, burgerImageView: ImageView) {
        //Play Animation
        val burgerPosition = getPositionOf(burgerImageView)
        val cartPosition = getPositionOf(ivCart)

        val imageSize = 80
        val viewToAnimate = setUpViewToAnimate(
            burger,
            burgerImageView, imageSize
        )

        flRoot.addView(viewToAnimate)

        val xAnimator = ObjectAnimator.ofFloat(
            viewToAnimate,
            View.TRANSLATION_X, burgerPosition[0].toFloat()
            , (cartPosition[0] - (imageSize * 2)).toFloat()
        )
        xAnimator.duration = 500

        val yAnimator = ObjectAnimator.ofFloat(
            viewToAnimate,
            View.TRANSLATION_Y, burgerPosition[1].toFloat()
            , (cartPosition[1] - (4 * imageSize)).toFloat()
        )
        yAnimator.duration = 500

        val alphaAnimator = ObjectAnimator.ofFloat(
            viewToAnimate,
            View.ALPHA, 0f, 1f
        )
        alphaAnimator.duration = 500

        val scaleXAnimator = ObjectAnimator.ofFloat(
            viewToAnimate,
            View.SCALE_X, 1f, 0.25f
        )

        val scaleYAnimator = ObjectAnimator.ofFloat(
            viewToAnimate,
            View.SCALE_Y, 1f, 0.25f
        )

        AnimatorSet().apply {
            play(xAnimator).with(yAnimator)
                .with(alphaAnimator).with(scaleXAnimator).with(scaleYAnimator)
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    flRoot.removeView(viewToAnimate)
                }
            })
            start()
        }
    }

    private fun getPositionOf(view: View?): IntArray {
        val position = intArrayOf(0, 0)
        view?.getLocationOnScreen(position)
        return position
    }

    private fun setUpViewToAnimate(
        item: BurgerVO,
        imageView: ImageView,
        imageSize: Int
    ): ImageView {
        //1
        val viewToAnimate = ImageView(applicationContext)

        //2
        Glide.with(this)
            .load(item.burgerImageUrl)
            .into(viewToAnimate)

        //3
        val layoutParams = imageView.layoutParams
        layoutParams.height = imageView.height
        layoutParams.width = imageView.width
        viewToAnimate.layoutParams = layoutParams

        //4
        viewToAnimate.alpha = 0f


        return viewToAnimate
    }
}
