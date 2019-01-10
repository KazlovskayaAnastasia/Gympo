package com.gmail.martsulgp.gympo.presentation.menu.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.gmail.martsulgp.gympo.R
import com.gmail.martsulgp.gympo.data.model.entity.SetsFeed
import com.gmail.martsulgp.gympo.extras.CustomDateUtils

class SetsAdapter(
//        private val activity: FragmentActivity,
//        private val viewModel: SetsViewModel,
        private var sets: List<SetsFeed>?) : RecyclerView.Adapter<SetsAdapter.Holder>() {
    private var itemCount = 0

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var setNum: TextView
        internal var setRepeats: TextView
        internal var setWeight: TextView
        internal var reqTime: TextView
        internal var restTime: TextView
        internal var moreBtn: ImageButton


        init {
            setNum = itemView.findViewById(R.id.set_number)
            setRepeats = itemView.findViewById(R.id.set_repeats)
            setWeight = itemView.findViewById(R.id.setWeight)
            reqTime = itemView.findViewById(R.id.setReqTime)
            restTime = itemView.findViewById(R.id.setRestTime)
            moreBtn = itemView.findViewById(R.id.set_moreBtn)
        }

        fun bind(item: SetsFeed) {
            setNum.text = "${item.setNumber}"
            setRepeats.text = "Repeats: ${item.repsNum}"
            setWeight.text = "Weight: ${item.repWeight}"
            reqTime.text = "Req. time: ${CustomDateUtils.millisToTime(item.reqTime)}"
            restTime.text = "Rest time: ${CustomDateUtils.millisToTime(item.restTime)}"
//            moreBtn.setOnClickListener { v -> viewModel.menuAction(moreBtn, position) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val root = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_set, parent, false)
        return Holder(root)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(sets!![position])
    }

    override fun getItemCount(): Int {
        return itemCount
    }

    fun dataChanged(sets: List<SetsFeed>) {
        this.sets = sets
        notifyDataSetChanged()
        itemCount = sets.size
    }


}
