package com.example.android.projectiii.challenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.projectiii.employee.Employee
import com.example.android.projectiii.R
import com.example.android.projectiii.database.ChallengeDatabase
import com.example.android.projectiii.databinding.FragmentCurrentChallengeBinding

class ChallengeListFragment : Fragment() {
    private lateinit var employee: Employee

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreateView(inflater, container, savedInstanceState)
        val binding: FragmentCurrentChallengeBinding =
            DataBindingUtil.inflate(inflater,
                R.layout.fragment_current_challenge, container, false)

        val list = mutableListOf<Challenges>()
        employee = Employee("Name", "Email", "123456", 35)

        val listener = object : ChallengeRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(challenge: Challenges) {
                employee.addCoins(challenge.coins)
                binding.allUserCoins.text = employee.totalCoins.toString()
            }
        }

        binding.allUserCoins.text = employee.totalCoins.toString()

        val newList: MutableList<Challenges> = mutableListOf()

        for (item in list) {
            if (item.isLocked == false) {
                newList.add(item)
            }
        }

        val adapter = ChallengeRecyclerViewAdapter(this.activity!!.applicationContext, newList, listener)

        binding.currentChallengesList.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.currentChallengesList.adapter = adapter

        return binding.root
    }
}
