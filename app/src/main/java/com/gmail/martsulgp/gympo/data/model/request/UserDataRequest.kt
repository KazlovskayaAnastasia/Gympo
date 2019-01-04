package com.gmail.martsulgp.gympo.data.model.request

data class UserDataRequest(
        var name: String,
        var surname: String,
        var age: Int,
        var height: Int,
        var weight: Int,
        var experience: Int,
        var aim: Int
)