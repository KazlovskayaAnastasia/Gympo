package com.gmail.martsulgp.gympo.data.model.entity

object UserData {
    /*lateinit*/ var name: String = ""
    /*lateinit*/ var surname: String = ""
    /*lateinit*/ var email: String = ""
    /*lateinit*/ var ownerId: String = "7C9E4B84-3C8F-7733-FF6D-EBB5AC0F6500"
    /*lateinit*/ var objectId: String = ""
    /*lateinit*/ var token: String = ""

    fun clearData(){
        name = ""
        surname = ""
        email = ""
        ownerId = ""
        objectId = ""
        token = ""
    }
}