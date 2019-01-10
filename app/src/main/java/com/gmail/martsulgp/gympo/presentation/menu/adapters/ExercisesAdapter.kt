package com.gmail.martsulgp.gympo.presentation.menu.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gmail.martsulgp.gympo.R
import com.gmail.martsulgp.gympo.data.model.entity.ExercisesFeed


class ExercisesAdapter(
//        private val activity: FragmentActivity,
//        private val viewModel: ExercisesViewModel,
        private var exercises: List<ExercisesFeed>?) : RecyclerView.Adapter<ExercisesAdapter.Holder>() {

    override fun getItemCount(): Int {
        return itemCount
    }

    private var itemCount = 0

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var exerciseName: TextView
        internal var exerciseSets: TextView
        internal var exerciseStart: ImageView
        internal var moreBtn: ImageButton


        init {
            exerciseName = itemView.findViewById(R.id.exercise_name)
            exerciseSets = itemView.findViewById(R.id.exercise_sets)
            exerciseStart = itemView.findViewById(R.id.exercise_start)
            moreBtn = itemView.findViewById(R.id.exercise_moreBtn)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val root = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_exercise, parent, false)
        return Holder(root)
    }

    override fun onBindViewHolder(holder: ExercisesAdapter.Holder, position: Int) {
        holder.exerciseName.text = exercises!![position].exerciseName
//        holder.exerciseSets.setText(activity.getString(R.string.sets, exercises!![position].setsNum))
//        holder.moreBtn.setOnClickListener { v -> viewModel.menuAction(holder.moreBtn, position) }
//        holder.exerciseStart.setOnClickListener { v -> viewModel.onPlayClick(position) }
//        holder.itemView.setOnClickListener({ v -> viewModel.goFurther(position) })
    }

    fun dataChanged(exercises: List<ExercisesFeed>) {
        this.exercises = exercises
        notifyDataSetChanged()
        itemCount = exercises.size
    }


}
