package com.gmail.martsulgp.gympo.presentation.menu.adapters

import androidx.recyclerview.widget.DiffUtil
import com.gmail.martsulgp.gympo.data.model.entity.TrainingsFeed

class TrainingsFeedDiffUtilCallback(
        private val oldList: List<TrainingsFeed>, private val newList: List<TrainingsFeed>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldTraining = oldList[oldItemPosition]
        val newTraining = oldList[oldItemPosition]
        return (oldTraining.created == newTraining.created)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldTraining = oldList[oldItemPosition]
        val newTraining = oldList[oldItemPosition]
        return oldTraining == newTraining
    }
}
