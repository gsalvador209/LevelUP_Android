package com.tanucode.levelup.presentation.ui.store

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tanucode.levelup.databinding.ItemProductBinding
import com.tanucode.levelup.domain.model.Product
import com.tanucode.levelup.util.Constants

class StoreAdapter(
    private val onBuyClick: (Product) -> Unit
) : ListAdapter<Product, StoreAdapter.Holder>(DIFF) {

    private var purchasedIds: Set<String> = emptySet()

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(a: Product, b: Product) = a.id == b.id
            override fun areContentsTheSame(a: Product, b: Product) = a == b
        }
    }

    inner class Holder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Product) {
            binding.tvTitle.text    = item.title
            binding.tvCategory.text = item.category
            binding.tvPrice.text    = "$ ${item.price}"
            Glide.with(binding.ivThumb)
                .load(item.imageUrl)
                .into(binding.ivThumb)

            val bought = purchasedIds.contains(item.id)
            binding.btnBuy.apply {
                text = if (bought) "✓" else "Buy"
                isEnabled = !bought
                setOnClickListener {
                    Log.d(Constants.LOGS_MESSAGE,"Clicked buy on ${item.id}")
                    if (!bought) onBuyClick(item)
                }
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        ))

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setPurchasedIds(ids: Set<String>) {
        purchasedIds = ids
        notifyDataSetChanged()
    }

}
