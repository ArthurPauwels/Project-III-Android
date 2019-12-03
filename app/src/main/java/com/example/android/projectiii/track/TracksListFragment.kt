package com.example.android.projectiii.track

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.projectiii.R
import com.example.android.projectiii.database.ProjectDatabase
import com.example.android.projectiii.databinding.FragmentTracksBinding
import com.example.android.projectiii.employee.EmployeeRepository
import com.example.android.projectiii.employee.EmployeeViewModel
import com.example.android.projectiii.employee.EmployeeViewModelFactory

class TracksListFragment : Fragment() {
    private lateinit var trackViewModel: TrackViewModel
    private lateinit var employeeViewModel: EmployeeViewModel
    private lateinit var binding: FragmentTracksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_tracks,
            container,
            false
        )
        val instance = ProjectDatabase.getInstance(requireContext())
        val trackDao = instance.trackDao
        //val challengeDao = instance.challengeDao
        val employeeDao = instance.employeeDao
        val trackViewModelFactory =
            TrackViewModelFactory(
                TrackRepository(trackDao)
            )
        val employeeViewModelFactory =
            EmployeeViewModelFactory(
                EmployeeRepository(employeeDao)
            )

        trackViewModel = ViewModelProviders.of(this, trackViewModelFactory).get(
            TrackViewModel::class.java
        )
        employeeViewModel = ViewModelProviders.of(this, employeeViewModelFactory).get(
            EmployeeViewModel::class.java
        )
        binding.lifecycleOwner = this

        binding.employeeViewModel = employeeViewModel

        val adapter = TrackRecyclerViewAdapter(employeeViewModel, trackViewModel)

        trackViewModel.trackList.observe(this, Observer { listTracks ->
            adapter.submitList(listTracks)
        })
        trackViewModel.updated.observe(this, Observer { updated ->
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