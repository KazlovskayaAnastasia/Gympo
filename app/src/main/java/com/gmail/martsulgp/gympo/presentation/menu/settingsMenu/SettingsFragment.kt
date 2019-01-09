package com.gmail.martsulgp.gympo.presentation.menu.settingsMenu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import com.arellomobile.mvp.MvpFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.arellomobile.mvp.presenter.ProvidePresenterTag
import com.gmail.martsulgp.gympo.R
import com.gmail.martsulgp.gympo.data.model.entity.UserDataObj.token
import com.gmail.martsulgp.gympo.data.repository.UserDataRepository
import com.gmail.martsulgp.gympo.presentation.auth.login.LoginFragment
import org.koin.android.ext.android.inject

class SettingsFragment : MvpFragment(), SettingsView {

    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var presenter: SettingsPresenter

    private val userDataRepository: UserDataRepository by inject()

    @ProvidePresenterTag(presenterClass = SettingsPresenter::class, type = PresenterType.GLOBAL)
    fun provideDialogPresenterTag(): String = "Settings"

    @ProvidePresenter(type = PresenterType.GLOBAL)
    fun provideDialogPresenter() = SettingsPresenter(userDataRepository)

    @BindView(R.id.btn_logout)
    lateinit var btnLogOut: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.settings_fragment, container, false)
        ButterKnife.bind(this, view)
        val bundle = this.arguments

        btnLogOut.setOnClickListener {
            logOut()
        }

        return view
    }

    override fun logOut() {
        presenter.onLogOutPress(token)
    }

    override fun goToLoginScreen() {
        val intent = Intent(activity, LoginFragment::class.java)
        startActivity(intent)
    }
}