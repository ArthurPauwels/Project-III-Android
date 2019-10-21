package com.example.android.projectiii

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.FieldPosition

class MyRecyclerViewAdapter(val data: ArrayList<String>) : RecyclerView.Adapter<TextItemViewHolder>() {

    override fun getItemCount() = data.size

    override fun onBindViewHolder (holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        print(item)
        holder.textView.text = item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.text_item_view, parent, false) as TextView
        return TextItemViewHolder(view)
    }
}