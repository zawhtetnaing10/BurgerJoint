package com.zg.burgerjoint.activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.transition.Fade
import android.view.View
import android.view.Window
import android.widget.ImageView
import androidx.core.app.ActivityCompat
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

class MainActivity : BaseActivity(), MainView {

    private lateinit var mBurgerAdapter: BurgerAdapter
    private lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpAnimations()
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setUpPresenter()
        setUpListeners()
        setUpRecycler()
        mPresenter.onUIReady(this)
    }

    private fun setUpAnimations(){
        with(window) {
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            val fade = Fade()
            fade.duration = 600
            exitTransition = fade
        }
    }

    private fun setUpListeners() {
        ivCart.setOnClickListener {
            mPresenter.onTapCart()
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

    override fun navigateToBurgerDetailsScreenWithAnimation(burgerId: Int, burgerImageView: ImageView) {
        val imagePair = Pair.create(burgerImageView as View, "tBurgerImage")
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,imagePair)
        ActivityCompat.startActivity(this,BurgerDetailsActivity.newIntent(this,burgerId),options.toBundle())
    }

    override fun navigateToCartScreen() {
        startActivity(CartActivity.newIntent(this)
            , ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle())
    }

    override fun displayBurgerList(burgerList: List<BurgerVO>) {
        mBurgerAdapter.setNewData(burgerList)
    }

    override fun displayCountInCart(burgersInCartCount: Int) {
        tvCartCount.text = burgersInCartCount.toString()
    }

    override fun animateAddBurgerToCart(burger: BurgerVO, burgerImageView: ImageView) {
        //Play Animation
        val burgerPosition = getPositionOf(burgerImageView)
        val cartPosition = getPositionOf(ivCart)

        val foodImageSize = 80
        val viewToAnimate = setUpViewToAnimate(burger, burgerImageView, foodImageSize)
        flContainer.addView(viewToAnimate)

        val xAnimator = ObjectAnimator.ofFloat(viewToAnimate, View.TRANSLATION_X,
            burgerPosition[0].toFloat(), cartPosition[0].toFloat() - foodImageSize*2)
        xAnimator.duration = 500
        val yAnimator =  ObjectAnimator.ofFloat(viewToAnimate, View.TRANSLATION_Y,
            burgerPosition[1].toFloat(), cartPosition[1].toFloat() - foodImageSize*3)
        yAnimator.duration = 500
        val alphaAnimator = ObjectAnimator.ofFloat(viewToAnimate, View.ALPHA,
            0f, 1f)
        val scaleXAnimator = ObjectAnimator.ofFloat(viewToAnimate,View.SCALE_X,
            1f, 0.25f)
        scaleXAnimator.duration = 500
        val scaleYAnimator = ObjectAnimator.ofFloat(viewToAnimate,View.SCALE_Y,
            1f, 0.25f)
        scaleYAnimator.duration = 500

        AnimatorSet().apply {
            play(xAnimator).with(yAnimator).with(alphaAnimator).with(scaleXAnimator).with(scaleYAnimator)
            addListener(object: AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    flContainer.removeView(viewToAnimate)
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
        imageSize : Int
    ): ImageView {
        val viewToAnimate = ImageView(applicationContext)
        Glide.with(this)
            .load(item.burgerImageUrl)
            .into(viewToAnimate)
        val layoutParams = imageView.layoutParams
        layoutParams.height = imageView.height
        layoutParams.width = imageView.width
        viewToAnimate.layoutParams = layoutParams
        viewToAnimate.alpha = 0f
        return viewToAnimate
    }
}
