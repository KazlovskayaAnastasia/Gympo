package com.gmail.martsulgp.gympo.presentation.menu.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gmail.martsulgp.gympo.R
import com.gmail.martsulgp.gympo.data.model.entity.TrainingsFeed
import com.gmail.martsulgp.gympo.extras.CustomDateUtils
import com.gmail.martsulgp.gympo.extras.WeekdaysEnum


class TrainingsAdapter : RecyclerView.Adapter<TrainingsAdapter.Holder>() {

    private var trainings: List<TrainingsFeed> = arrayListOf()
    private var itemCount = 0

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var trainingDay: TextView = itemView.findViewById(R.id.training_day)
        internal var trainingCreated: TextView = itemView.findViewById(R.id.training_created)
        internal var trainingName: TextView = itemView.findViewById(R.id.training_name)
        internal var trainingTime: TextView = itemView.findViewById(R.id.training_time)
        internal var trainingComplexity: RatingBar = itemView.findViewById(R.id.training_complexity)
        internal var moreBtn: ImageButton = itemView.findViewById(R.id.training_moreBtn)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val root = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_training, parent, false)
        return Holder(root)
    }

    override fun onBindViewHolder(holder: TrainingsAdapter.Holder, position: Int) {
        holder.trainingDay.text = WeekdaysEnum.convertIntToShortDay(trainings[position].weekday)
        //TODO remove with date convertor
        holder.trainingCreated.text = fieldSelector(position)
        holder.trainingTime.text = CustomDateUtils.millisToTime(trainings[position].time)
        holder.trainingName.text = trainings[position].trainingName
        holder.trainingComplexity.rating = trainings[position].complexity
//        holder.moreBtn.setOnClickListener { _ -> viewModel.menuAction(holder.moreBtn, position) }
//
//        holder.itemView.setOnClickListener { _ -> viewModel.goFurther(position) }

    }

    override fun getItemCount(): Int {
        return itemCount
    }

    fun dataChanged(trainings: List<TrainingsFeed>) {
        val diffUtillCallback = TrainingsFeedDiffUtilCallback(this.trainings, trainings)
        val result = DiffUtil.calculateDiff(diffUtillCallback, true)
        this.trainings = trainings
        result.dispatchUpdatesTo(this)
        itemCount = trainings.size
    }

    private fun fieldSelector(position: Int): String {
        return if (trainings[position].updated != 0L) {
            "updated: " + CustomDateUtils.millisToDate(trainings[position].updated)
        } else {
            "created: " + CustomDateUtils.millisToDate(trainings[position].created)
        }
    }


}
