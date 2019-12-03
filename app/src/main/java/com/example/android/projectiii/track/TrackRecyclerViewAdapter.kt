package com.example.android.projectiii.track

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.projectiii.databinding.TrackCardBinding
import com.example.android.projectiii.employee.EmployeeViewModel

class TrackRecyclerViewAdapter(private val employeeViewModel: EmployeeViewModel) :
    ListAdapter<Track, RecyclerView.ViewHolder>(TrackDiffCallback()) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val track = getItem(position)

        (holder as ViewHolder).bind(track, employeeViewModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            TrackCardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    class ViewHolder(val binding: TrackCardBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.item2?.let { track ->
                    it.findNavController().navigate(
                        TracksListFragmentDirections.actionCurrentTracksToCurrentTrack(track.id)
                    )
                    binding.item = track.test()
                    binding.item2 = track
                }
            }
        }

        fun bind(item: Track, employeeViewModel: EmployeeViewModel) {
            binding.item = item.test()
            binding.item2 = item
            binding.employeeViewModel = employeeViewModel
            binding.executePendingBindings()
        }
    }
}

private class TrackDiffCallback : DiffUtil.ItemCallback<Track>() {
    override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem == newItem
    }
}