package com.example.android.projectiii.expert

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.projectiii.R
import com.example.android.projectiii.databinding.FragmentExpertListBinding

class ExpertListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val binding: FragmentExpertListBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_expert_list, container, false)

        val expertList = mutableListOf<Expert>()



        val expert1 = Expert(
            "1",
            "expert1@email.com",
            "+3111111111",
            "Expert 1"
        )
        val expert2 = Expert(
            "2",
            "expert2@email.com",
            "+3122222222",
            "Expert 2"
        )
        val expert3 = Expert(
            "3",
            "expert3@email.com",
            "+3133333333",
            "Expert 3"
        )
        val expert4 = Expert(
            "4",
            "expert4@email.com",
            "+3144444444",
            "Expert 4"
        )
        val expert5 = Expert(
            "5",
            "expert5@email.com",
            "+3155555555",
            "Expert 5"
        )
        val expert6 = Expert(
            "6",
            "expert6@email.com",
            "+3166666666",
            "Expert 6"
        )
        val expert7 = Expert(
            "7",
            "expert7@email.com",
            "+3177777777",
            "Expert 7"
        )
        val expert8 = Expert(
            "8",
            "expert8@email.com",
            "+3188888888",
            "Expert 8"
        )
        val expert9 = Expert(
            "9",
            "expert9@email.com",
            "+3199999999",
            "Expert 9"
        )
        val expert10 = Expert(
            "10",
            "expert10@email.com",
            "+31101010",
            "Expert 10"
        )

        expertList.add(expert1)
        expertList.add(expert2)
        expertList.add(expert3)
        expertList.add(expert4)
        expertList.add(expert5)
        expertList.add(expert6)
        expertList.add(expert7)
        expertList.add(expert8)
        expertList.add(expert9)
        expertList.add(expert10)


        val listener = object : ExpertRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(expert: Expert) {
                val intent = Intent(activity, ExpertContactView::class.java)
                intent.putExtra("email", expert.email)
                intent.putExtra("phone", expert.phone)
                intent.putExtra("name", expert.name)
                startActivity(intent)
            }
        }

        val adapter =
            ExpertRecyclerViewAdapter(expertList, listener)

        binding.expertList.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.expertList.adapter = adapter


        return binding.root
    }
}
