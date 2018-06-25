package com.gmail.martsulgp.workoutpartner.model.entity

import com.google.gson.annotations.SerializedName

data class Training(
        val complexity: Float,
        val trainingName: String,
        val weekday: Int,
        val time: Long,
        val updated: Long,
        val created: Long,
        val ownerId: String,
        val objectId: String,
        val exercises: List<Exercise>
)