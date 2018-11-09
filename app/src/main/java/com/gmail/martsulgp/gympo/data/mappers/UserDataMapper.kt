package com.gmail.martsulgp.gympo.data.mappers

import com.gmail.martsulgp.gympo.data.model.entity.UserData
import com.gmail.martsulgp.gympo.data.model.response.UserDataResponse

object UserDataMapper{
    fun map(userDataResponse: UserDataResponse) = UserData(
            name = userDataResponse.name ?: "UserName",
            surname = userDataResponse.surname ?: "UserSurname",
            email = userDataResponse.email ?: "UserEmail",
            ownerId = userDataResponse.ownerId ?: "UserId",
            objectId = userDataResponse.objectId ?: "ObjId",
            token = userDataResponse.token ?: "UserToken"
    )

}


