package com.gmail.martsulgp.gympo.data.repository

import com.gmail.martsulgp.gympo.data.model.request.UserDataRequest
import com.gmail.martsulgp.gympo.data.model.response.UserDataResponse
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface UserDataRepository {
    fun logInUser(email: String, password: String) : Observable<UserDataResponse>
    fun registerUser(email: String, password: String, name: String, surname: String) : Observable<UserDataResponse>
    fun updateUserData(user: UserDataRequest) : Completable
    fun loginWithFB(token: String) : Single<UserDataResponse>
}