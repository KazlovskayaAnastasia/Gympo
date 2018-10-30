package com.gmail.martsulgp.workoutpartner.data.repository

import com.gmail.martsulgp.workoutpartner.data.model.response.UserInfoResponse
import io.reactivex.Observable

interface UserDataRepository {
    fun setLogInData(email: String, password: String) : Observable<UserInfoResponse>
    fun setRegisterData(email: String, password: String, name: String, surname: String) : Observable<UserInfoResponse>
}