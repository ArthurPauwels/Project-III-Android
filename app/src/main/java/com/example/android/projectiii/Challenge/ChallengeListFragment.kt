package com.example.android.projectiii.Challenge

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.projectiii.Employee.Employee
import com.example.android.projectiii.R
import com.example.android.projectiii.databinding.ChallengeListBinding

class ChallengeListFragment : Fragment() {
    private lateinit var employee: Employee

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding: ChallengeListBinding =
            DataBindingUtil.inflate(inflater,
                R.layout.challenge_list, container, false)

        val list = ChallengeMockData().getChallengesList()

        employee = Employee("Name", "Email", "123456", 35)

        val listener = object : ChallengeRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(challenge: Challenges) {
                employee.addCoins(challenge.coins)
                binding.allUserCoins.text = employee.totalCoins.toString()
            }
        }


        binding.allUserCoins.text = employee.totalCoins.toString()


        val adapter = ChallengeRecyclerViewAdapter(this.activity!!.applicationContext, list, listener)

        binding.currentChallengesList.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.currentChallengesList.adapter = adapter

        return binding.root
    }
}
