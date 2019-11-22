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
            "Paarems@email.com",
            "Dietician",
            "A. Paarens"
        )
        val expert2 = Expert(
            "2",
            "Lammens@email.com",
            "Chiropractor",
            "B. Lammens"
        )

        expertList.add(expert1)
        expertList.add(expert2)

        val listener = object : ExpertRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(expert: Expert) {
                val intent = Intent(activity, ExpertContactView::class.java)
                intent.putExtra("email", expert.email)
                intent.putExtra("profession", expert.profession)
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
