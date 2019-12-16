package com.example.android.projectiii.track

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
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
            if (trackViewModel.trackList.value != null) {
                var newTrackList = mutableListOf<Track>()

                for (track in (trackViewModel.trackList.value as List<Track>)) {
                    if (!track.isComplete()) {
                        newTrackList.add(track)
                    }
                }

                Log.d("CHECK2", "HERE")
                Log.d("CHECK2", (trackViewModel.trackList.value as List<Track>).size.toString())
                Log.d("CHECK2", newTrackList.size.toString())
                if ((trackViewModel.trackList.value as List<Track>).size == newTrackList.size) {
                    adapter.notifyDataSetChanged()
                } else {
                    adapter.submitList(newTrackList)
                }
            }
        })
        employeeViewModel.isUpdated.observe(this, Observer { isUpdated ->
            binding.invalidateAll()
        })

        binding.currentTracksList.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.currentTracksList.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Current tracks"
    }
}
