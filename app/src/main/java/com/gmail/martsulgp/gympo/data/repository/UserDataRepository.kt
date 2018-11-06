package com.gmail.martsulgp.gympo.data.repository

import com.gmail.martsulgp.gympo.data.model.response.UserInfoResponse
import io.reactivex.Observable
import io.reactivex.Single

interface UserDataRepository {
    fun setLogInData(email: String, password: String) : Observable<UserInfoResponse>
    fun setRegisterData(email: String, password: String, name: String, surname: String) : Observable<UserInfoResponse>
    fun loginWithFB(token: String) : Single<UserInfoResponse>
}