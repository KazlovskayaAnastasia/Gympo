package com.gmail.martsulgp.gympo.data.model.entity

import java.io.Serializable

data class UserData(
        var name: String,
        var surname: String,
        var email: String,
        var age: Int,
        var aim: Int,
        var experience: Int,
        var height: Int?,
        var weight: Int,
        var ownerId: String,
        var objectId: String,
        var token: String

) : Serializable