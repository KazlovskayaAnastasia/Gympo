package com.gmail.martsulgp.workoutpartner.presentation

import android.os.Bundle
import android.util.Log
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.arellomobile.mvp.presenter.ProvidePresenterTag
import com.gmail.martsulgp.workoutpartner.R
import com.gmail.martsulgp.workoutpartner.data.UserDataRepository
import org.koin.android.ext.android.inject

class MainActivity : MvpAppCompatActivity(), MainView {
    override fun logger(message : String, debugLevel : MainPresenter.DebugLevel) {
        when(debugLevel){
            MainPresenter.DebugLevel.DEBUG -> Log.d(TAG, message)
            MainPresenter.DebugLevel.ERROR -> Log.e(TAG, message)
        }
    }

    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var presenter: MainPresenter

    private val userDataRepository: UserDataRepository by inject()

    @ProvidePresenterTag(presenterClass = MainPresenter::class, type = PresenterType.GLOBAL)
    fun provideDialogPresenterTag(): String = "Main"

    @ProvidePresenter(type = PresenterType.GLOBAL)
    fun provideDialogPresenter() = MainPresenter(userDataRepository)

    override fun showProgressBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgressBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onPageLoaded()
    }

    companion object {
        const val TAG = "MainActivityTag"
    }

}
