package com.gmail.martsulgp.workoutpartner.presentation.registry

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.gmail.martsulgp.workoutpartner.R
import com.gmail.martsulgp.workoutpartner.data.UserDataRepository
import org.koin.android.ext.android.inject

class LoginFragment : MvpAppCompatFragment(), LoginView {

    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var presenter: LoginPresenter

    private val userDataRepository: UserDataRepository by inject()

    @ProvidePresenter(type = PresenterType.GLOBAL)
    fun provideDialogPresenter() = LoginPresenter(userDataRepository)


    @BindView(R.id.layout_signInEditLogin)
    lateinit var layout_signInEditLogin: TextInputLayout
    @BindView(R.id.signInEditLogin)
    lateinit var signInEditLogin: TextInputEditText
    @BindView(R.id.layout_signInEditPassword)
    lateinit var layout_signInEditPassword: TextInputLayout
    @BindView(R.id.signInEditPassword)
    lateinit var signInEditPassword: TextInputEditText
    @BindView(R.id.signInButton)
    lateinit var signInButton: Button
    @BindView(R.id.goToSignUp)
    lateinit var goToSignUp: TextView
    @BindView(R.id.loginProgressBar)
    lateinit var loginProgressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun progressBarVisibility(b: Boolean) {
        loginProgressBar.visibility = if (b) View.VISIBLE else View.GONE
    }

}