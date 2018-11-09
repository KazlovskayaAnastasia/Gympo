package com.gmail.martsulgp.gympo.data.model.entity

object UserDataObj {
    lateinit var name: String
    lateinit var surname: String
    lateinit var email: String
    lateinit var ownerId: String
    lateinit var objectId: String
    lateinit var token: String

    fun clearData() {
        name = ""
        surname = ""
        email = ""
        ownerId = ""
        objectId = ""
        token = ""
    }

    fun setData(user: UserData) {
        this.name = user.name
        this.surname = user.surname
        this.email = user.email
        this.ownerId = user.ownerId
        this.objectId = user.objectId
        this.token = user.token
    }
}