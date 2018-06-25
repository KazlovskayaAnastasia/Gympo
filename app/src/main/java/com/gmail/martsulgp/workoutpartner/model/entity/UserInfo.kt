package com.gmail.martsulgp.workoutpartner.model.entity

object UserInfo {
    lateinit var name: String
    lateinit var surname: String
    lateinit var email: String
    lateinit var ownerId: String
    lateinit var objectId: String
    lateinit var token: String

    fun clearData(){
        name = ""
        surname = ""
        email = ""
        ownerId = ""
        objectId = ""
        token = ""
    }
}