package com.gmail.martsulgp.workoutpartner.presentation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gmail.martsulgp.workoutpartner.R
import com.gmail.martsulgp.workoutpartner.data.UserDataRepository
import com.gmail.martsulgp.workoutpartner.data.UserDataRepositoryImpl
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataRepository: UserDataRepository = UserDataRepositoryImpl()
        dataRepository.setLogInData("martsulg.p@gmail.com", "1111")

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { }
                .doOnTerminate { }
                .subscribe(
                        { it -> Log.d("AAAAAA",it.name) },
                        { error -> Log.e("AAAAA",error.message) }
                )
    }

}
