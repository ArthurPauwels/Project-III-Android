package com.example.android.projectiii.expert

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.android.projectiii.R

class ExpertRecyclerViewAdapter(
    private val data: MutableList<Expert>,
    private val clickHandler: OnItemClickListener
) : RecyclerView.Adapter<ExpertRecyclerViewAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(expert: Expert)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        holder.name.text = item.name
        holder.email.text = item.email
        holder.profession.text = item.profession
        holder.bind(item, clickHandler)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.expert_item_view, parent, false) as LinearLayout
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById(R.id.tv_name) as TextView
        val email = itemView.findViewById(R.id.tv_email) as TextView
        val profession = itemView.findViewById(R.id.tv_profession) as TextView

        fun bind(item: Expert, listener: OnItemClickListener) {
            itemView.setOnClickListener(View.OnClickListener {
                it.findNavController().navigate(
                    ExpertListFragmentDirections.actionExpertListToExpertContactView(item.name, item.profession, item.email)
                )
            })
        }
    }
}