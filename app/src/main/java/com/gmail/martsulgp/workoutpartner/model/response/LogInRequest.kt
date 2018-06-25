package com.gmail.martsulgp.workoutpartner.model.response

import com.google.gson.annotations.SerializedName


data class LogInRequest(
        @SerializedName("login")
        val email: String,
        @SerializedName("password")
        val password: String
)