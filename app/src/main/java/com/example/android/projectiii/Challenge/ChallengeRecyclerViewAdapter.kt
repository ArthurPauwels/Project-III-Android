package com.example.android.projectiii.Challenge

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.projectiii.R

class ChallengeRecyclerViewAdapter (val context: Context, private val data: MutableList<Challenges>, private val listener: OnItemClickListener) : RecyclerView.Adapter<ChallengeRecyclerViewAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(challenges: Challenges)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder (holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.textChallengeTitle.text = item.title
        holder.textChallengeDescription.text = item.description
        holder.textChallengeCoins.text = item.coins.toString()
        holder.textChallengeTimeLimit.text = item.deadline
        holder.checkboxChallenge.isChecked = item.isDone
        holder.imageChallenge.setImageResource(context.resources.getIdentifier(item.image, "drawable", context.packageName))
        holder.bind(item, listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.first_item_view, parent, false) as LinearLayout
        return ViewHolder(view)
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textChallengeTitle = itemView.findViewById(R.id.challengeTitle) as TextView
        val textChallengeDescription = itemView.findViewById(R.id.challengeDescription) as TextView
        val textChallengeTimeLimit = itemView.findViewById(R.id.challengeLimitTime) as TextView
        val textChallengeCoins = itemView.findViewById(R.id.challengeCoins) as TextView
        val checkboxChallenge = itemView.findViewById(R.id.challengeCheckBox) as CheckBox
        val imageChallenge = itemView.findViewById(R.id.challengeImage) as ImageView

        fun bind(item: Challenges, listener: OnItemClickListener) {
            itemView.setOnClickListener(View.OnClickListener {
                if (textChallengeDescription.visibility == View.GONE) {
                    textChallengeDescription.visibility = View.VISIBLE
                } else {
                    textChallengeDescription.visibility = View.GONE
                }

                listener.onItemClick(item)
            })
        }
    }
}