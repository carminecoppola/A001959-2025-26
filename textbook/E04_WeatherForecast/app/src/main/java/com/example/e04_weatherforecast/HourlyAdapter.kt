package com.example.e04_weatherforecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.e04_weatherforecast.databinding.ItemHourlyBinding

class HourlyAdapter : ListAdapter<HourlyEntry, HourlyAdapter.VH>(
    object : DiffUtil.ItemCallback<HourlyEntry>() {
        override fun areItemsTheSame(a: HourlyEntry, b: HourlyEntry) = a.dateTime == b.dateTime
        override fun areContentsTheSame(a: HourlyEntry, b: HourlyEntry) = a == b
    }
) {
    class VH(private val binding: ItemHourlyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(entry: HourlyEntry) {
            binding.tvTime.text     = entry.displayTime
            binding.tvEmoji.text    = entry.weatherEmoji
            binding.tvTemp.text     = "${entry.t2c.toInt()}°C"
            binding.tvWind.text     = "${entry.winds} ${entry.ws10k.toInt()}km/h"
            binding.tvHumidity.text = "💧${entry.rh2.toInt()}%"
            binding.tvDesc.text     = entry.descriptionEn
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, type: Int) = VH(
        ItemHourlyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(h: VH, pos: Int) = h.bind(getItem(pos))
}