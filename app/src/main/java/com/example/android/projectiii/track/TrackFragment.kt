package com.example.android.projectiii.track

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.projectiii.R
import com.example.android.projectiii.challenge.ChallengeRecyclerViewAdapter
import com.example.android.projectiii.database.ProjectDatabase
import com.example.android.projectiii.databinding.FragmentTrackBinding
import com.example.android.projectiii.employee.EmployeeRepository
import com.example.android.projectiii.employee.EmployeeViewModel
import com.example.android.projectiii.employee.EmployeeViewModelFactory

class TrackFragment : Fragment() {
    private lateinit var trackViewModel: TrackViewModel
    private lateinit var employeeViewModel: EmployeeViewModel
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

        val instance = ProjectDatabase.getInstance(requireContext())
        //val challengeDao = instance.challengeDao
        val employeeDao = instance.employeeDao
        val trackDao = instance.trackDao

        val trackViewModelFactory =
            TrackViewModelFactory(
                TrackRepository(trackDao)
            )
        val employeeViewModelFactory =
            EmployeeViewModelFactory(
                EmployeeRepository(employeeDao)
            )
        trackViewModel = ViewModelProviders.of(this, trackViewModelFactory).get(TrackViewModel::class.java)
        employeeViewModel = ViewModelProviders.of(this, employeeViewModelFactory).get(EmployeeViewModel::class.java)
        binding.lifecycleOwner = this
        binding.employeeViewModel = employeeViewModel

        val adapter = ChallengeRecyclerViewAdapter(employeeViewModel)
        trackViewModel.trackList.observe(this, Observer { listTracks ->
            val t = listTracks.find { t -> t.id == args.trackId }
            if (t !== null){
                adapter.submitList(t.challenges)
            }
            binding.setClickListener {
                    it.findNavController().navigate(
                        TrackFragmentDirections.actionCurrentTrackToExpertFragment("Baekens A.","Deontoloog","BaekansA@hotmail.com")
                    )
            }
        })
        employeeViewModel.isUpdated.observe(this, Observer { isUpdated ->
            binding.invalidateAll()
        })

        binding.currentChallengesList.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.currentChallengesList.adapter = adapter

        return binding.root
    }
}
