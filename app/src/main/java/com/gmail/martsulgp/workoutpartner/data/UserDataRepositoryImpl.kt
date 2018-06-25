package com.gmail.martsulgp.workoutpartner.data

import com.gmail.martsulgp.workoutpartner.model.response.LogInRequest
import com.gmail.martsulgp.workoutpartner.model.response.RegisterRequest
import com.gmail.martsulgp.workoutpartner.model.response.UserInfoResponse
import io.reactivex.Observable

class UserDataRepositoryImpl : UserDataRepository {
    override fun setLogInData(email: String, password: String) : Observable<UserInfoResponse> =
        BackendlessApi.create().logInUser(LogInRequest(
                email = email,
                password = password
        ))


    override fun setRegisterData(email: String, password: String, name: String, surname: String) : Observable<UserInfoResponse> =
            BackendlessApi.create().regUser(RegisterRequest(
                    email = email,
                    password = password,
                    name = name,
                    surname = surname
            ))

}