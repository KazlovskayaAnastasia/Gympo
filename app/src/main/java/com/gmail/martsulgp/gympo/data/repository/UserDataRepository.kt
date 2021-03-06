package com.gmail.martsulgp.gympo.data.repository

import com.gmail.martsulgp.gympo.data.model.entity.UserData
import com.gmail.martsulgp.gympo.data.model.request.UserDataRequest
import io.reactivex.Observable
import io.reactivex.Single

interface UserDataRepository {
    fun logInUser(email: String, password: String): Observable<UserData>
    fun registerUser(email: String, password: String, name: String, surname: String): Observable<UserData>
    fun updateUserData(user: UserDataRequest): Observable<UserData>
    fun loginWithFB(token: String): Single<UserData>
    fun checkToken(token: String): Single<Boolean>
    fun getUserInfo(id: String): Observable<UserData>
    fun logOut(token: String): Observable<Void>
}