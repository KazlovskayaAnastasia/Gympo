package com.gmail.martsulgp.gympo.presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.gmail.martsulgp.gympo.R
import com.gmail.martsulgp.gympo.data.model.entity.UserDataObj
import com.gmail.martsulgp.gympo.data.repository.UserDataRepository
import com.gmail.martsulgp.gympo.presentation.auth.AuthActivity
import com.gmail.martsulgp.gympo.presentation.menu.MainMenuActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject


class StartActivity : Activity() {

    @BindView(R.id.iv_gif)
    lateinit var iv_gif: ImageView

    companion object {
        lateinit var pref: SharedPreferences
        val PREF_NAME = "sharedPreferences"
        val USER_TOKEN = "token"
        val USER_ID = "id"
    }

    private val userDataRepository: UserDataRepository by inject()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        pref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        ButterKnife.bind(this)

        Glide.with(this)
                .load("https://loading.io/spinners/gear-set/lg.triple-gears-loading-icon.gif")
                .into(iv_gif)

        try {
            var result = false
            Handler().postDelayed({
                userDataRepository.checkToken(pref.getString(USER_TOKEN, ""))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe{
                                 it ->
                                    if (it) {
                                        getUserInfo()
                                    } else {
                                        goNext(AuthActivity())
                                    }
                        }
            }, 1000L)

        } catch (e: Exception) {
            Log.e("StartActivity msg: ", e.toString())
        }
    }

    fun goNext(activity: Activity) {
        val intent = Intent(this@StartActivity, activity::class.java)
        startActivity(intent)
        this.finish()
    }

    @SuppressLint("CheckResult")
    fun getUserInfo(){
        userDataRepository.getUserInfo(pref.getString(USER_ID, ""))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { it ->
                            UserDataObj.setData(it)
                            goNext(MainMenuActivity())
                        },
                        { _ -> goNext(AuthActivity()) }
                )
    }
}