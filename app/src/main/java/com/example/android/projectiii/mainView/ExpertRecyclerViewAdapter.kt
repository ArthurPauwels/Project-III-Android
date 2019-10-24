package com.example.android.projectiii.mainView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.projectiii.Expert
import com.example.android.projectiii.R

class ExpertRecyclerViewAdapter (val data: ArrayList<Expert>, val clickHandler: OnItemClickListener) : RecyclerView.Adapter<ExpertRecyclerViewAdapter.ViewHolder>() {

    interface OnItemClickListener{
        fun onItemClick(experts: Expert)
    }

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        holder.name.text = item.name
        holder.email.text = item.email
        holder.phone.text = item.phone

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.expert_item_view, parent, false) as LinearLayout
        return ViewHolder(view)
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById(R.id.tv_name) as TextView
        val email = itemView.findViewById(R.id.tv_email) as TextView
        val phone = itemView.findViewById(R.id.tv_phone) as TextView
    }
}