package com.example.android.projectiii.Level

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.projectiii.Challenge.ChallengeRecyclerViewAdapter
import com.example.android.projectiii.Challenge.Challenges
import com.example.android.projectiii.R

class LevelRecyclerViewAdapter(private val data: MutableList<Levels>, val clickHandler: OnItemClickListener) : RecyclerView.Adapter<LevelRecyclerViewAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(challenges: Challenges)
    }

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun getItemCount() = data.size

    override fun onBindViewHolder (holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.textViewName.text = item.level

        val layoutManager = LinearLayoutManager(holder.recyclerViewChallenges.context, RecyclerView.VERTICAL, false)
        layoutManager.initialPrefetchItemCount = item.challenges.size

        val listener = object :
            ChallengeRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(challenges: Challenges) {
                clickHandler.onItemClick(challenges)
            }
        }

        val challengeAdapter =
            ChallengeRecyclerViewAdapter(
                item.challenges,
                listener
            )

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