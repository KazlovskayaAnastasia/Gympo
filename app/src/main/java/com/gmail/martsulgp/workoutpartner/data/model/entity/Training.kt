package com.gmail.martsulgp.workoutpartner.data.model.entity

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