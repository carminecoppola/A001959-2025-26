package com.example.l15roomdatabasedemo

import androidx.recyclerview.widget.RecyclerView
import com.example.l15roomdatabasedemo.databinding.ItemRowBinding

/**
 * ViewHolder for a single Room item row.
 *
 * RecyclerView reuses ViewHolders while scrolling, improving performance.
 */
class ItemViewHolder(
    private val binding: ItemRowBinding
) : RecyclerView.ViewHolder(binding.root) {

    /**
     * Binds database data to the row UI.
     */
    fun bind(item: Item) {
        binding.tvTitle.text = item.title
        binding.tvSubtitle.text = "Created at: ${item.createdAt}"
    }
}