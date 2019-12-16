package com.example.android.projectiii.challenge

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
import com.example.android.projectiii.challenge.viewmodel.ChallengeViewModel
import com.example.android.projectiii.challenge.viewmodel.ChallengeViewModelFactory
import com.example.android.projectiii.database.ProjectDatabase
import com.example.android.projectiii.databinding.FragmentCurrentChallengeBinding
import com.example.android.projectiii.employee.EmployeeRepository
import com.example.android.projectiii.employee.EmployeeViewModel
import com.example.android.projectiii.employee.EmployeeViewModelFactory
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChallengeListFragment : Fragment() {
    private val challengeViewModel: ChallengeViewModel by viewModel()
    private val employeeViewModel: EmployeeViewModel by viewModel()
    private lateinit var binding: FragmentCurrentChallengeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_current_challenge,
            container,
            false
        )

        binding.lifecycleOwner = this

        binding.employeeViewModel = employeeViewModel

        val adapter = ChallengeRecyclerViewAdapter(employeeViewModel)

        challengeViewModel.challengeList.observe(this, Observer { listChallenges ->
            val newList: MutableList<Challenge> = mutableListOf()

            for (challenge in listChallenges) {
                if (!challenge.isLocked) {
                    newList.add(challenge)
                }
            }
            adapter.submitList(newList)
        })

        employeeViewModel.isUpdated.observe(this, Observer { isUpdated ->
            binding.invalidateAll()
        })

        binding.currentChallengesList.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.currentChallengesList.adapter = adapter

        return binding.root
    }
}
