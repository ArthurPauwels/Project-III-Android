package com.example.android.projectiii.shop

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.projectiii.databinding.ShopItemCardBinding
import com.example.android.projectiii.databinding.TrackCardBinding
import com.example.android.projectiii.employee.EmployeeViewModel

class ShopRecyclerViewAdapter(
    private val shopViewModel: ShopViewModel
) :
    ListAdapter<ShopItem, RecyclerView.ViewHolder>(ShopItemDiffCallback()) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        (holder as ViewHolder).bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ShopItemCardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), shopViewModel
        )
    }

    class ViewHolder(
        val binding: ShopItemCardBinding,
        val shopViewModel: ShopViewModel
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                Log.v("abc", "Clicked")
            }
        }

        fun bind(item: ShopItem) {
            binding.item = item
            binding.executePendingBindings()
        }
    }
}

private class ShopItemDiffCallback : DiffUtil.ItemCallback<ShopItem>() {
    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem._id == newItem._id
    }

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem == newItem
    }
}
