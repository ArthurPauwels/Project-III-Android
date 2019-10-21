package com.example.android.projectiii

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.security.AccessController.getContext

class MyRecyclerViewAdapter(val data: ArrayList<Levels>) : RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun getItemCount() = data.size

    override fun onBindViewHolder (holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.textViewName.text = item.level

        val layoutManager = LinearLayoutManager(holder.recyclerViewChallenges.context, RecyclerView.VERTICAL, false)
        layoutManager.initialPrefetchItemCount = item.challenges.size

        val challengeAdapter = SecondRecyclerViewAdapter(item.challenges)

        holder.recyclerViewChallenges.layoutManager = layoutManager
        holder.recyclerViewChallenges.adapter = challengeAdapter
        holder.recyclerViewChallenges.setRecycledViewPool(viewPool)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.first_item_view, parent, false) as LinearLayout
        return ViewHolder(view)
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName = itemView.findViewById(R.id.text_level_indicator) as TextView
        val recyclerViewChallenges = itemView.findViewById(R.id.recycler_view_challenges) as RecyclerView
    }
}