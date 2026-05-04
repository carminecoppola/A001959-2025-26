package com.example.l15roomdatabasedemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.l15roomdatabasedemo.databinding.ItemRowBinding

/**
 * Adapter connecting Room data to RecyclerView.
 *
 * ListAdapter uses DiffUtil internally, so only changed rows are redrawn.
 */
class ItemAdapter(
    private val onClick: (Item) -> Unit
) : ListAdapter<Item, ItemViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)

        /*
            For this classroom demo, clicking an item deletes it.
        */
        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }
}

/**
 * DiffUtil callback for Item objects.
 */
class ItemDiffCallback : DiffUtil.ItemCallback<Item>() {

    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}