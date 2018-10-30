package com.gmail.martsulgp.workoutpartner.data.model.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
        @SerializedName("email")
        val email: String,
        @SerializedName("password")
        val password: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("surname")
        val surname: String
)