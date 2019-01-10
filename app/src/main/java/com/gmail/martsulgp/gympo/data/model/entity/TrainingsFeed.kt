package com.gmail.martsulgp.gympo.data.model.entity

import com.google.gson.annotations.SerializedName

data class TrainingsFeed (
    var complexity: Float,
    var trainingName: String,
    var weekday: Int,
    var time: Long,
    var updated: Long,
    var created: Long,
    var ownerId: String,
    var objectId: String,
    @SerializedName("exercise")
    var exercises: List<ExercisesFeed>

    //    public List<ListExercises> getExercises() {
    //        return exercises;
    //    }
    //
    //    public void setExercises(List<ListExercises> exercises) {
    //        this.exercises = exercises;
    //    }
)
