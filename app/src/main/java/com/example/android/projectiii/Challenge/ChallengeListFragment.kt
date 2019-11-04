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
import com.example.android.projectiii.Level.LevelMockData
import com.example.android.projectiii.Level.Levels
import com.example.android.projectiii.R
import com.example.android.projectiii.databinding.ChallengeListBinding
import com.example.android.projectiii.Level.LevelRecyclerViewAdapter

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

        val list = LevelMockData().getLevelList()

        val listener = object : LevelRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(challenges: Challenges) {
                val intent = Intent(activity, ChallengeActivity::class.java)
                intent.putExtra("label", challenges.label)
                intent.putExtra("description", challenges.description)
                startActivity(intent)
            }
        }

        val adapter = LevelRecyclerViewAdapter(list, listener)

        binding.currentChallengesList.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.currentChallengesList.adapter = adapter

        return binding.root
    }
}
