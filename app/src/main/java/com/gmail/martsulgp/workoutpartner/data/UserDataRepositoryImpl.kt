package com.gmail.martsulgp.workoutpartner.data

import com.gmail.martsulgp.workoutpartner.model.entity.UserData
import com.gmail.martsulgp.workoutpartner.model.request.UserDataRequest
import com.gmail.martsulgp.workoutpartner.model.response.LogInRequest
import com.gmail.martsulgp.workoutpartner.model.response.RegisterRequest
import com.gmail.martsulgp.workoutpartner.model.response.UserDataResponse
import io.reactivex.Completable
import io.reactivex.Observable

class UserDataRepositoryImpl : UserDataRepository {
    private val api = BackendlessApi.create()
    private val currentUser = UserData

    override fun updateUserData(user: UserDataRequest): Completable =
        api.updateUser(currentUser.ownerId, user)


    override fun logInUser(email: String, password: String) : Observable<UserDataResponse> =
       api.logInUser(LogInRequest(
                email = email,
                password = password
        ))


    override fun registerUser(email: String, password: String, name: String, surname: String) : Observable<UserDataResponse> =
            api.regUser(RegisterRequest(
                    email = email,
                    password = password,
                    name = name,
                    surname = surname
            ))

}