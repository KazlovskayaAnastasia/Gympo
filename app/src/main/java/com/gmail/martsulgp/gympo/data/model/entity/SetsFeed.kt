package com.gmail.martsulgp.gympo.data.model.entity



data class SetsFeed (
    var restTime: Long,
    var repWeight: Int,
    var created: Long,
    var updated: Long,
    var reqTime: Long,
    var setNumber: Int,
    var repsNum: Int,
    var ownerId: String,
    var objectId: String
)
