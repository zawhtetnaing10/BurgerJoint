package com.zg.burgerjoint.activities

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.zg.burgerjoint.R
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, GameActivity::class.java)
        }
    }

    private var xPositionDiff = 0f
    private var yPositionDiff = 0f

//    private val springForce: SpringForce by lazy {
//        SpringForce(0f).apply {
//            stiffness = SpringForce.STIFFNESS_HIGH
//            dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
//        }
//    }
//
//    private val springAnimationX: SpringAnimation by lazy {
//        SpringAnimation(ivGameBurger, DynamicAnimation.TRANSLATION_X).setSpring(springForce)
//    }
//
//    private val springAnimationY : SpringAnimation by lazy{
//        SpringAnimation(ivGameBurger,DynamicAnimation.TRANSLATION_Y).setSpring(springForce)
//    }

    private val burgerGestureListener = object : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            burgerFlingAnimationX.setStartVelocity(velocityX)
            burgerFlingAnimationY.setStartVelocity(velocityY)

            burgerFlingAnimationX.start()
            burgerFlingAnimationY.start()



            return true
        }
    }

    private val burgerGestureDetector: GestureDetector by lazy {
        GestureDetector(this, burgerGestureListener)
    }

    private val burgerFlingAnimationX: FlingAnimation by lazy {
        FlingAnimation(ivGameBurger, DynamicAnimation.X).setFriction(1f)
    }

    private val burgerFlingAnimationY: FlingAnimation by lazy {
        FlingAnimation(ivGameBurger, DynamicAnimation.Y).setFriction(1f)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        Glide.with(this)
            .load("https://vignette.wikia.nocookie.net/simpsons-restaurants/images/2/20/Spicy_Clucker.png/revision/latest?cb=20131125185837")
            .into(ivGameBurger)

        setUpTouchListener()
        setUpAnimatingBlock()
        setUpTreeObserver()
        setUpAnimationEndListener()
    }



    private fun setUpTreeObserver() {
        ivGameBurger.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val displayMetrics = DisplayMetrics()
                windowManager.defaultDisplay.getMetrics(displayMetrics)
                val width = displayMetrics.widthPixels
                val height = displayMetrics.heightPixels

                burgerFlingAnimationX.setMinValue(0f)
                    .setMaxValue((width - ivGameBurger.width).toFloat())
                burgerFlingAnimationY.setMinValue(0f)
                    .setMaxValue((height - (ivGameBurger.height)).toFloat())

                ivGameBurger.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    private fun setUpAnimationEndListener(){
        burgerFlingAnimationY.addEndListener { _, _, _, _ ->
            if (areViewsOverlapping(ivGameBurger, block)){
                Snackbar.make(window.decorView, "YOU WIN!!!", Snackbar.LENGTH_LONG).show()
            }
        }
    }


    private fun areViewsOverlapping(v1: View, v2: View): Boolean {
        val rect1 = Rect()
        v1.getHitRect(rect1)

        val rect2 = Rect()
        v2.getHitRect(rect2)

        return Rect.intersects(rect1, rect2)
    }

    private fun setUpAnimatingBlock() {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels

        ObjectAnimator.ofFloat(
            block, View.TRANSLATION_X
            , 0f
            , width.toFloat() - resources.getDimension(R.dimen.block_width)
        )
            .apply {
                interpolator = AccelerateDecelerateInterpolator()
                repeatCount = ObjectAnimator.INFINITE
                duration = 500L
                repeatMode = ObjectAnimator.REVERSE
                start()
            }
    }

    private fun setUpTouchListener() {
//        ivGameBurger.setOnTouchListener { view , motionEvent ->
//            when(motionEvent.action){
//                MotionEvent.ACTION_DOWN ->{
//                    springAnimationY.cancel()
//                    springAnimationX.cancel()
//                    xPositionDiff = motionEvent.rawX - view.x
//                    yPositionDiff = motionEvent.rawY - view.y
//                }
//                MotionEvent.ACTION_MOVE -> {
//                    ivGameBurger.x = motionEvent.rawX - xPositionDiff
//                    ivGameBurger.y = motionEvent.rawY - yPositionDiff
//                }
//                MotionEvent.ACTION_UP -> {
//                    springAnimationX.start()
//                    springAnimationY.start()
//                }
//            }
//            true
//        }
        ivGameBurger.setOnTouchListener { _, motionEvent ->
            burgerGestureDetector.onTouchEvent(motionEvent)
        }
    }


}
