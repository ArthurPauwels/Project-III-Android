package com.example.android.projectiii.Challenge

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.projectiii.R
import com.example.android.projectiii.databinding.ChallengeListBinding

class ChallengeListFragment : Fragment() {

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

        val listener = object : ChallengeRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(challenge: Challenges) {

            }
        }

        val adapter = ChallengeRecyclerViewAdapter(list, listener)

        binding.currentChallengesList.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.currentChallengesList.adapter = adapter

        return binding.root
    }
}
