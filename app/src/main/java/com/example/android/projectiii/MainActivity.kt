package com.example.android.projectiii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.projectiii.databinding.ActivityMainBinding
import com.example.android.projectiii.mainView.MyRecyclerViewAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val list = ArrayList<Levels>()

        val chal = Challenges("Label", "description")
        val chalList = ArrayList<Challenges>()

        chalList.add(chal)
        chalList.add(chal)
        chalList.add(chal)
        chalList.add(chal)

        list.add(Levels("1", chalList))
        list.add(Levels("2", chalList))
        list.add(Levels("3", chalList))

        val listener = object : MyRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(challenges: Challenges) {
                println(challenges.label)
            }
        }

        val adapter = MyRecyclerViewAdapter(list, listener)

        binding.currentChallengesList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.currentChallengesList.adapter = adapter
    }
}
