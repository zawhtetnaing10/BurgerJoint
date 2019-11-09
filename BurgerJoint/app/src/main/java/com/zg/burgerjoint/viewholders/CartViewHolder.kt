package com.zg.burgerjoint.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.zg.burgerjoint.data.vos.BurgerVO
import com.zg.burgerjoint.delegates.CartViewHolderActionDelegate
import kotlinx.android.synthetic.main.activity_burger_details.*
import kotlinx.android.synthetic.main.view_item_burger.view.tvBurgerName
import kotlinx.android.synthetic.main.view_item_burger.view.tvDescription
import kotlinx.android.synthetic.main.view_item_cart.view.*

class CartViewHolder(itemView: View, private val mDelegate: CartViewHolderActionDelegate)
    : BaseViewHolder<BurgerVO>(itemView) {

    init {

        itemView.btnRemoveFromCart.setOnClickListener {
            mData?.let {
                mDelegate.onTapRemoveFromCart(it)
            }
        }
    }

    override fun bindData(data: BurgerVO) {
        mData = data

        itemView.tvBurgerName.text = data.burgerName
        itemView.tvDescription.text = data.burgerDescription

        Glide.with(itemView.ivBurger)
            .load(data.burgerImageUrl)
            .into(itemView.ivBurger)
    }
}