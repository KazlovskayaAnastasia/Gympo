package com.gmail.martsulgp.gympo.presentation.auth.registry

import android.annotation.SuppressLint
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.gmail.martsulgp.gympo.data.model.entity.UserData
import com.gmail.martsulgp.gympo.data.model.entity.UserDataObj
import com.gmail.martsulgp.gympo.data.repository.UserDataRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
class RegistryPresenter(private val userDataRepository: UserDataRepository) : MvpPresenter<RegistryView>() {

    @SuppressLint("CheckResult")
    fun onRegistryPress(email: String, password: String, name: String, surname: String){
        userDataRepository.registerUser(email,password,name,surname)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.progressBarVisibility(true) }
                .doOnTerminate { viewState.progressBarVisibility(false) }
                .subscribe(
                        { it: UserData ->
                            viewState.logger(it.email , DebugLevel.DEBUG)
                            viewState.logger(it.name , DebugLevel.DEBUG)
                            viewState.logger(it.surname , DebugLevel.DEBUG)
                            UserDataObj.setData(it)
                            viewState.goToRegistryDetails()
                        },
                        { _ ->
                            Log.e("Register data", "Error")
                        }
                )
    }
    enum class DebugLevel {
        DEBUG,
        ERROR
    }
}