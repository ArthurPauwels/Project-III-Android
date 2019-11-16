package com.example.android.projectiii.challenge

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.projectiii.databinding.ChallengeCardBinding
import com.example.android.projectiii.employee.EmployeeViewModel

class ChallengeRecyclerViewAdapter(private val employeeViewModel: EmployeeViewModel) : ListAdapter<Challenges, RecyclerView.ViewHolder>(ChallengeDiffCallback()) {

    interface OnItemClickListener {
        fun onItemClick(challenges: Challenges)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val challenge = getItem(position)
        (holder as ViewHolder).bind(challenge, employeeViewModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(ChallengeCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    class ViewHolder(val binding: ChallengeCardBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.item?.let { challenge ->
                    challenge.isOpen = !challenge.isOpen
                    binding.item = challenge
                }
            }
        }
        fun bind(item: Challenges, employeeViewModel: EmployeeViewModel) {
            binding.item = item
            binding.employeeViewModel = employeeViewModel
            binding.executePendingBindings()
        }
    }
}

private class ChallengeDiffCallback : DiffUtil.ItemCallback<Challenges>() {
    override fun areItemsTheSame(oldItem: Challenges, newItem: Challenges): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: Challenges, newItem: Challenges): Boolean {
        return oldItem == newItem
    }
}