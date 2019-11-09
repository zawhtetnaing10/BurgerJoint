package com.zg.burgerjoint.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.zg.burgerjoint.R
import com.zg.burgerjoint.data.vos.BurgerVO
import com.zg.burgerjoint.delegates.BurgerViewHolderActionDelegate
import com.zg.burgerjoint.viewholders.BurgerViewHolder

class BurgerAdapter(private val mDelegate : BurgerViewHolderActionDelegate) : BaseRecyclerAdapter<BurgerViewHolder, BurgerVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BurgerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item_burger,parent,false)
        return BurgerViewHolder(view, mDelegate)
    }
}