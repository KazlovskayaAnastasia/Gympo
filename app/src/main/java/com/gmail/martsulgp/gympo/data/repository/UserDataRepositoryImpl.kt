package com.gmail.martsulgp.gympo.data.repository

import com.gmail.martsulgp.gympo.data.model.request.LogInRequest
import com.gmail.martsulgp.gympo.data.model.request.RegisterRequest
import com.gmail.martsulgp.gympo.data.model.response.UserInfoResponse
import com.gmail.martsulgp.gympo.data.service.BackendlessApi
import io.reactivex.Observable
import io.reactivex.Single

class UserDataRepositoryImpl : UserDataRepository {
    override fun loginWithFB(token: String): Single<UserInfoResponse> =
        BackendlessApi.create().loginUserWithFB(token)


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