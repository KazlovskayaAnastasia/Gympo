package com.gmail.martsulgp.gympo.data.repository

import com.gmail.martsulgp.gympo.data.mappers.UserDataMapper
import com.gmail.martsulgp.gympo.data.model.entity.UserData
import com.gmail.martsulgp.gympo.data.model.entity.UserDataObj
import com.gmail.martsulgp.gympo.data.model.request.LogInRequest
import com.gmail.martsulgp.gympo.data.model.request.RegisterRequest
import com.gmail.martsulgp.gympo.data.model.request.UserDataRequest
import com.gmail.martsulgp.gympo.data.service.BackendlessApi
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class UserDataRepositoryImpl : UserDataRepository {

    private val api by lazy { BackendlessApi.create() }
    private val currentUser = UserDataObj

    override fun loginWithFB(token: String): Single<UserData> =
            api.loginUserWithFB(token).map { UserDataMapper.map(userDataResponse = it) }

    override fun checkToken(token: String): Single<Boolean> =
        api.checkToken(token)


    override fun updateUserData(user: UserDataRequest): Completable =
        api.updateUser(currentUser.ownerId, user)


    override fun logInUser(email: String, password: String) : Observable<UserData> =
       api.logInUser(LogInRequest(
                email = email,
                password = password
        )).map { UserDataMapper.map(userDataResponse = it) }


    override fun registerUser(email: String, password: String, name: String, surname: String) : Observable<UserData> =
            api.regUser(RegisterRequest(
                    email = email,
                    password = password,
                    name = name,
                    surname = surname
            )).map { UserDataMapper.map(userDataResponse = it) }
}