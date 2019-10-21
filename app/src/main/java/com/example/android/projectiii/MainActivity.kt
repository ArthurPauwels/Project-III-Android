package com.example.android.projectiii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.projectiii.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val list = ArrayList<String>()

        list.add("toto")
        list.add("dede")
        list.add("dzdz")

        val adapter = MyRecyclerViewAdapter(list)

        binding.currentChallengesList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.currentChallengesList.adapter = adapter
    }
}
