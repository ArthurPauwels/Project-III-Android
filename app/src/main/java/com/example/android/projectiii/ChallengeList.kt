package com.example.android.projectiii

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.projectiii.databinding.ActivityMainBinding
import com.example.android.projectiii.mainView.MyRecyclerViewAdapter

class ChallengeList : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.inflate(inflater, R.layout.challenge_list, container, false)

        val list = ArrayList<Levels>()


        val chal = Challenges("id", "Label", "description")
        val chal2 = Challenges("id2", "Label2", "description2")
        val chal3 = Challenges("id3", "Label3", "description3")
        val chalList = ArrayList<Challenges>()

        chalList.add(chal)
        chalList.add(chal2)
        chalList.add(chal3)
        chalList.add(chal)

        list.add(Levels("1", chalList))
        list.add(Levels("2", chalList))
        list.add(Levels("3", chalList))

        val listener = object : MyRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(challenges: Challenges) {
                val intent = Intent(activity, ChallengeActivity::class.java)
                intent.putExtra("label", challenges.label)
                intent.putExtra("description", challenges.description)
                startActivity(intent)
            }
        }

        val adapter = MyRecyclerViewAdapter(list, listener)

        binding.currentChallengesList.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.currentChallengesList.adapter = adapter

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}
