package com.example.android.projectiii.track

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.projectiii.R
import com.example.android.projectiii.challenge.ChallengeRecyclerViewAdapter
import com.example.android.projectiii.databinding.FragmentTrackBinding
import com.example.android.projectiii.employee.EmployeeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrackFragment : Fragment() {
    private val trackViewModel: TrackViewModel by viewModel()
    private val employeeViewModel: EmployeeViewModel by viewModel()
    private lateinit var binding: FragmentTrackBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = TrackFragmentArgs.fromBundle(arguments!!)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_track,
            container,
            false
        )

        binding.lifecycleOwner = this
        binding.employeeViewModel = employeeViewModel

        val adapter = ChallengeRecyclerViewAdapter(employeeViewModel)
        trackViewModel.trackList.observe(this, Observer { listTracks ->
            val t = listTracks.find { t -> t.id == args.trackId }
            if (t !== null) {
                adapter.submitList(t.getIncompleteChallenges())
            }

            binding.setClickListener {
                it.findNavController().navigate(
                    TrackFragmentDirections.actionCurrentTrackToExpertFragment(
                        "Baekens A.",
                        "Deontoloog",
                        "BaekansA@hotmail.com"
                    )
                )
            }
        })

        employeeViewModel.isUpdated.observe(this, Observer { isUpdated ->
            binding.invalidateAll()
        })

        binding.currentChallengesList.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.currentChallengesList.adapter = adapter

        return binding.root
    }
}
