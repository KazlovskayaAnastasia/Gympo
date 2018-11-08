package com.gmail.martsulgp.gympo.data.model.response

import com.google.gson.annotations.SerializedName

data class UserDataResponse(
        var name: String? = null,
        var surname: String? = null,
        var email: String? = null,
        var ownerId: String? = null,
        var objectId: String? = null,
        @SerializedName("user-token")
        var token: String? = null
)