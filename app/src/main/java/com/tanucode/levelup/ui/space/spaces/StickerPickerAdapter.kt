package com.tanucode.levelup.presentation.ui.space

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tanucode.levelup.databinding.ItemStickerPickerBinding
import com.tanucode.levelup.domain.model.Product

class StickerPickerAdapter(
    private val onClick: (String) -> Unit
) : ListAdapter<Product, StickerPickerAdapter.Holder>(DIFF) {

    companion object {
        private val DIFF = object: DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(a: Product, b: Product) = a.id == b.id
            override fun areContentsTheSame(a: Product, b: Product) = a == b
        }
    }

    inner class Holder(val b: ItemStickerPickerBinding)
        : RecyclerView.ViewHolder(b.root) {
        fun bind(item: Product) {
            Glide.with(b.ivThumb).load(item.imageUrl).into(b.ivThumb)
            b.root.setOnClickListener {
                onClick(item.imageUrl)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(ItemStickerPickerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}
