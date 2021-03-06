package com.gmail.martsulgp.gympo.data.model.request

import com.google.gson.annotations.SerializedName

data class LogInRequest(
        @SerializedName("login")
        val email: String,
        @SerializedName("password")
        val password: String
)