package com.example.android.projectiii

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.adapters.CardViewBindingAdapter
import androidx.recyclerview.widget.RecyclerView

class SecondRecyclerViewAdapter (val data: ArrayList<Challenges>) : RecyclerView.Adapter<SecondRecyclerViewAdapter.ViewHolder>() {

    override fun getItemCount() = data.size

    override fun onBindViewHolder (holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.textViewName.text = item.label
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.second_item_view, parent, false) as LinearLayout
        return ViewHolder(view)
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName = itemView.findViewById(R.id.text_challenge_name) as TextView
    }
}