package com.gmail.martsulgp.workoutpartner.data.repository

import com.gmail.martsulgp.workoutpartner.data.service.BackendlessApi
import com.gmail.martsulgp.workoutpartner.data.model.request.LogInRequest
import com.gmail.martsulgp.workoutpartner.data.model.request.RegisterRequest
import com.gmail.martsulgp.workoutpartner.data.model.response.UserInfoResponse
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