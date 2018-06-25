package com.gmail.martsulgp.workoutpartner.data

import com.gmail.martsulgp.workoutpartner.model.response.UserInfoResponse
import io.reactivex.Observable

interface UserDataRepository {
    fun setLogInData(email: String, password: String) : Observable<UserInfoResponse>
    fun setRegisterData(email: String, password: String, name: String, surname: String) : Observable<UserInfoResponse>
}