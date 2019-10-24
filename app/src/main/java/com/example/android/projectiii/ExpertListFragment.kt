package com.example.android.projectiii

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.projectiii.databinding.FragmentExpertListBinding
import com.example.android.projectiii.mainView.ExpertRecyclerViewAdapter
import com.example.android.projectiii.mainView.MyRecyclerViewAdapter

class ExpertListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val binding: FragmentExpertListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_expert_list, container, false)

        val expertList = ArrayList<Expert>()

        val expert1 = Expert("1", "expert1@email.com", "+3111111111", "Expert 1")
        val expert2 = Expert("2", "expert2@email.com", "+3122222222", "Expert 2")
        val expert3 = Expert("3", "expert3@email.com", "+3133333333", "Expert 3")
        val expert4 = Expert("4", "expert4@email.com", "+3144444444", "Expert 4")
        val expert5 = Expert("5", "expert5@email.com", "+3155555555", "Expert 5")

        expertList.add(expert1)
        expertList.add(expert2)
        expertList.add(expert3)
        expertList.add(expert4)
        expertList.add(expert5)

        val listener = object : ExpertRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(expert: Expert) {
                val intent = Intent(activity, ExpertContactView::class.java)
                intent.putExtra("email", expert.email)
                intent.putExtra("phone", expert.phone)
                intent.putExtra("name", expert.name)
                startActivity(intent)
            }
        }

        val adapter = ExpertRecyclerViewAdapter(expertList, listener)

        binding.expertList.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.expertList.adapter = adapter


        return binding.root
    }
}
