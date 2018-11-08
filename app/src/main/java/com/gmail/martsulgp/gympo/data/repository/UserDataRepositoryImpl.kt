package com.gmail.martsulgp.gympo.data.repository

import com.gmail.martsulgp.gympo.data.model.entity.UserData
import com.gmail.martsulgp.gympo.data.model.request.LogInRequest
import com.gmail.martsulgp.gympo.data.model.request.RegisterRequest
import com.gmail.martsulgp.gympo.data.model.request.UserDataRequest
import com.gmail.martsulgp.gympo.data.model.response.UserDataResponse
import com.gmail.martsulgp.gympo.data.service.BackendlessApi
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class UserDataRepositoryImpl : UserDataRepository {

    private val api = BackendlessApi.create()
    private val currentUser = UserData

    override fun loginWithFB(token: String): Single<UserDataResponse> =
            api.loginUserWithFB(token)

    override fun checkToken(token: String): Single<Boolean> =
        api.checkToken(token)


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