package com.gmail.martsulgp.gympo.presentation.auth.registry

import android.annotation.SuppressLint
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.gmail.martsulgp.gympo.data.model.request.UserDataRequest
import com.gmail.martsulgp.gympo.data.repository.UserDataRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
class RegistryDetailsPresenter(val userDataRepository: UserDataRepository) : MvpPresenter<RegistryDetailsView>() {


    @SuppressLint("CheckResult")
    fun updateUser(user: UserDataRequest) {
        userDataRepository.updateUserData(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.progressBarVisibility(true) }
                .doOnTerminate { viewState.progressBarVisibility(false) }
                .subscribe(
                        { Log.d("UserDataUpdate", "Completable completed") },
                        { error -> Log.d("UserDataUpdate", "Completable onError: ${error.message.toString()}") }
                )
    }

    fun onSaveBtnClick() {
        viewState.onSaveClick()
    }

    fun showAlert(b: Boolean) {
        if (b)
            viewState.showAlertDialog(null)
    }
}