package com.example.android.projectiii.track

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.projectiii.R
import com.example.android.projectiii.databinding.FragmentTracksBinding
import com.example.android.projectiii.employee.EmployeeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TracksListFragment : Fragment() {
    private val trackViewModel: TrackViewModel by viewModel()
    private val employeeViewModel: EmployeeViewModel by viewModel()
    private lateinit var binding: FragmentTracksBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_tracks,
            container,
            false
        )

        binding.lifecycleOwner = this
        binding.employeeViewModel = employeeViewModel
        binding.trackViewModel = trackViewModel

        val adapter = TrackRecyclerViewAdapter(employeeViewModel, trackViewModel)
        trackViewModel.trackList.observe(this, Observer { listTracks ->
            adapter.submitList(listTracks)
        })
        trackViewModel.isUpdated.observe(this, Observer { updated ->
            adapter.notifyDataSetChanged()
        })
        employeeViewModel.isUpdated.observe(this, Observer { isUpdated ->
            binding.invalidateAll()
        })

        binding.currentTracksList.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.currentTracksList.adapter = adapter

        return binding.root
    }
}
