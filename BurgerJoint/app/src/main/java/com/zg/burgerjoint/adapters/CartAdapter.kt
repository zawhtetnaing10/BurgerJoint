package com.zg.burgerjoint.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.zg.burgerjoint.R
import com.zg.burgerjoint.data.vos.BurgerVO
import com.zg.burgerjoint.delegates.CartViewHolderActionDelegate
import com.zg.burgerjoint.viewholders.BurgerViewHolder
import com.zg.burgerjoint.viewholders.CartViewHolder

class CartAdapter(private val mDelegate : CartViewHolderActionDelegate) : BaseRecyclerAdapter<CartViewHolder, BurgerVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item_cart,parent,false)
        return CartViewHolder(view, mDelegate)
    }
}