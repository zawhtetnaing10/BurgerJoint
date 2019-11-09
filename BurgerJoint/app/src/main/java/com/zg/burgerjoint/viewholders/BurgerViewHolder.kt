package com.zg.burgerjoint.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.zg.burgerjoint.data.vos.BurgerVO
import com.zg.burgerjoint.delegates.BurgerViewHolderActionDelegate
import kotlinx.android.synthetic.main.view_item_burger.view.*

class BurgerViewHolder(itemView: View, private val mDelegate: BurgerViewHolderActionDelegate)
    : BaseViewHolder<BurgerVO>(itemView) {

    init {
        itemView.cvBurgerImage.setOnClickListener {
            mData?.let {
                mDelegate.onTapBurger(it, itemView.ivBurger)
            }
        }

        itemView.btnAddToCart.setOnClickListener {
            mData?.let {
                mDelegate.onTapAddToCart(it, itemView.ivBurger)
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