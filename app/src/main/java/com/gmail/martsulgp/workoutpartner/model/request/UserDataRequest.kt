package com.gmail.martsulgp.workoutpartner.model.request

import com.google.gson.annotations.SerializedName

data class UserDataRequest(
        var name: String,
        var surname: String,
        @SerializedName("age") // delete later
        var age: Int,
        var height: Int,
        var weight: Int,
        var experience: Int,
        @SerializedName("aim")
        var goal: Int
)