package com.gmail.martsulgp.gympo.data.model.response

import com.google.gson.annotations.SerializedName

data class SetsResponse(
        @SerializedName("restTime")
        var restTime: Long?,
        @SerializedName("repWeight")
        var repWeight: Int?,
        @SerializedName("created")
        var created: Long?,
        @SerializedName("reqTime")
        var reqTime: Long?,
        @SerializedName("ownerId")
        var ownerId: String?,
        @SerializedName("updated")
        var updated: Long?,
        @SerializedName("setNumber")
        var setNumber: Int?,
        @SerializedName("objectId")
        var objectId: String?,
        @SerializedName("repsNum")
        var repsNum: Int?
)