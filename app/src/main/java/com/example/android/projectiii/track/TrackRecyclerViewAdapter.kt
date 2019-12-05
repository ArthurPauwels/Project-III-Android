package com.example.android.projectiii.track

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.projectiii.databinding.TrackCardBinding
import com.example.android.projectiii.employee.EmployeeViewModel

class TrackRecyclerViewAdapter(private val employeeViewModel: EmployeeViewModel, private val trackViewModel: TrackViewModel) :
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
        , employeeViewModel, trackViewModel)
    }

    class ViewHolder(val binding: TrackCardBinding, val employeeViewModel: EmployeeViewModel, val trackViewModel: TrackViewModel) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.item2?.let { track ->
                    if (!track.isComplete()){
                        it.findNavController().navigate(
                            TracksListFragmentDirections.actionCurrentTracksToCurrentTrack(track.id)
                        )
                    }
                }
            }
            binding.setCbClickListener {
                binding.item?.let { chall ->
                    employeeViewModel.addCoins(chall.coins)
                }
                binding.item2?.let {track ->
                    trackViewModel.completeChallenge(track.id)
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