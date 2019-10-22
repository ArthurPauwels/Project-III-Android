package com.example.android.projectiii.mainView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.projectiii.Challenges
import com.example.android.projectiii.R

class SecondRecyclerViewAdapter (val data: ArrayList<Challenges>, val listener: OnItemClickListener) : RecyclerView.Adapter<SecondRecyclerViewAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(challenges: Challenges)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder (holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.textViewName.text = item.label
        holder.bind(item, listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.second_item_view, parent, false) as LinearLayout
        return ViewHolder(view)
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName = itemView.findViewById(R.id.text_challenge_name) as TextView

        fun bind(item: Challenges, listener: OnItemClickListener) {
            itemView.setOnClickListener(View.OnClickListener {
                listener.onItemClick(item)
            })
        }
    }
}