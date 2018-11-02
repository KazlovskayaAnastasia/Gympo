package com.gmail.martsulgp.workoutpartner.data

import com.gmail.martsulgp.workoutpartner.model.request.UserDataRequest
import com.gmail.martsulgp.workoutpartner.model.response.UserDataResponse
import io.reactivex.Completable
import io.reactivex.Observable

interface UserDataRepository {
    fun logInUser(email: String, password: String) : Observable<UserDataResponse>
    fun registerUser(email: String, password: String, name: String, surname: String) : Observable<UserDataResponse>
    fun updateUserData(user: UserDataRequest) : Completable
}