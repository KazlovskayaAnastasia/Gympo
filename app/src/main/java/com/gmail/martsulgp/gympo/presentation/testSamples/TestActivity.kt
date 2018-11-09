package com.gmail.martsulgp.gympo.presentation.testSamples

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import com.arellomobile.mvp.MvpActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.arellomobile.mvp.presenter.ProvidePresenterTag
import com.gmail.martsulgp.gympo.R
import com.gmail.martsulgp.gympo.data.repository.UserDataRepository
import com.gmail.martsulgp.gympo.presentation.auth.registry.RegistryDetailsFragment
import org.koin.android.ext.android.inject

class TestActivity : MvpActivity(), TestView {

    @BindView(R.id.mainProgress)
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.main_container, RegistryDetailsFragment()).commit()
        //presenter.onPageLoaded()
    }

    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var presenter: TestPresenter

    private val userDataRepository: UserDataRepository by inject()

    @ProvidePresenterTag(presenterClass = TestPresenter::class, type = PresenterType.GLOBAL)
    fun provideDialogPresenterTag(): String = "Main"

    @ProvidePresenter(type = PresenterType.GLOBAL)
    fun provideDialogPresenter() = TestPresenter(userDataRepository)

    override fun logger(message : String, debugLevel : TestPresenter.DebugLevel) {
        when(debugLevel){
            TestPresenter.DebugLevel.DEBUG -> Log.d(TAG, message)
            TestPresenter.DebugLevel.ERROR -> Log.e(TAG, message)
        }
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    companion object {
        const val TAG = "MainActivityTag"
    }
}
