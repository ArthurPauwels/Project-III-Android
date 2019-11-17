package com.example.android.projectiii.challenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.projectiii.employee.Employee
import com.example.android.projectiii.R
import com.example.android.projectiii.challenge.viewmodel.ChallengeViewModel
import com.example.android.projectiii.challenge.viewmodel.ChallengeViewModelFactory
import com.example.android.projectiii.database.ProjectDatabase
import com.example.android.projectiii.databinding.ChallengeListBinding
import com.example.android.projectiii.databinding.FragmentCurrentChallengeBinding
import com.example.android.projectiii.employee.EmployeeRepository
import com.example.android.projectiii.employee.EmployeeViewModel
import com.example.android.projectiii.employee.EmployeeViewModelFactory

class LockedChallengeFragment : Fragment() {
    private lateinit var employee: Employee
    private lateinit var employeeViewModel: EmployeeViewModel
    private lateinit var binding: ChallengeListBinding
    private lateinit var challengeViewModel: ChallengeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.challenge_list,
            container,
            false
        )

        val instance = ProjectDatabase.getInstance(requireContext())
        val challengeDao = instance.challengeDao
        val employeeDao = instance.employeeDao
        val challengeViewModelFactory =
            ChallengeViewModelFactory(
                ChallengeRepository(challengeDao)
            )
        val employeeViewModelFactory =
            EmployeeViewModelFactory(
                EmployeeRepository(employeeDao)
            )

        challengeViewModel = ViewModelProviders.of(this, challengeViewModelFactory).get(
            ChallengeViewModel::class.java)
        employeeViewModel = ViewModelProviders.of(this, employeeViewModelFactory).get(
            EmployeeViewModel::class.java)
        binding.lifecycleOwner = this

        val adapter = ChallengeRecyclerViewAdapter(employeeViewModel)

        challengeViewModel.challengesList.observe(this, Observer { listChallenges ->
            val newList: MutableList<Challenges> = mutableListOf()

            for (challenge in listChallenges) {
                if (challenge.isLocked) {
                    newList.add(challenge)
                }
            }
            adapter.submitList(newList)
        })

        binding.currentChallengesList.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.currentChallengesList.adapter = adapter

        return binding.root
    }

}
