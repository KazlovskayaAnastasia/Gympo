package com.gmail.martsulgp.workoutpartner.model.entity

import com.google.gson.annotations.SerializedName


data class Sets(
     var restTime: Long,
     var repWeight: Int,
     var created: Long,
     var reqTime: Long,
     var ownerId: String,
     var updated: Long,
     var setNumber: Int,
     var objectId: String,
     var repsNum: Int
)