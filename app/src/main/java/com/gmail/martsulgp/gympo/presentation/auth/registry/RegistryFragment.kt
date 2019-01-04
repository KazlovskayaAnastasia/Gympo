package com.gmail.martsulgp.gympo.presentation.auth.registry

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.arellomobile.mvp.MvpFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.arellomobile.mvp.presenter.ProvidePresenterTag
import com.gmail.martsulgp.gympo.R
import com.gmail.martsulgp.gympo.data.repository.UserDataRepository
import com.gmail.martsulgp.gympo.presentation.auth.AuthActivity
import com.gmail.martsulgp.gympo.presentation.auth.login.LoginFragment
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import org.koin.android.ext.android.inject

class RegistryFragment : MvpFragment(), GoogleApiClient.OnConnectionFailedListener, RegistryView {

    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var presenter: RegistryPresenter
    private val userDataRepository: UserDataRepository by inject()

    @ProvidePresenterTag(presenterClass = RegistryPresenter::class, type = PresenterType.GLOBAL)
    fun provideDialogPresenterTag(): String = "Registry"

    @ProvidePresenter(type = PresenterType.GLOBAL)
    fun provideDialogPresenter() = RegistryPresenter(userDataRepository)

    @BindView(R.id.layout_signInEditName)
    lateinit var layout_signInEditName: com.google.android.material.textfield.TextInputLayout
    @BindView(R.id.signInEditName)
    lateinit var signInEditName: com.google.android.material.textfield.TextInputEditText

    @BindView(R.id.layout_signUpEditSurname)
    lateinit var layout_signUpEditSurname: com.google.android.material.textfield.TextInputLayout
    @BindView(R.id.signUpEditSurname)
    lateinit var signUpEditSurname: com.google.android.material.textfield.TextInputEditText

    @BindView(R.id.layout_signUpEditEmail)
    lateinit var layout_signUpEditEmail: com.google.android.material.textfield.TextInputLayout
    @BindView(R.id.signUpEditEmail)
    lateinit var signUpEditEmail: com.google.android.material.textfield.TextInputEditText

    @BindView(R.id.layout_signUpEditPassword)
    lateinit var layout_signUpEditPassword: com.google.android.material.textfield.TextInputLayout
    @BindView(R.id.signUpEditPassword)
    lateinit var signUpEditPassword: com.google.android.material.textfield.TextInputEditText

    @BindView(R.id.SignUpButton)
    lateinit var SignUpButton: Button

    @BindView(R.id.goToSignIn)
    lateinit var goToSignIn: TextView

    @BindView(R.id.registryProgressBar)
    lateinit var registryProgressBar: ProgressBar

    companion object {
        private const val KEY_KEY = "KEY"
        const val REQEST_SIGN_IN = 9001
        const val TAG = "Google_Sign_In"
        fun newInstance(str: String): RegistryFragment {
            val fragment = RegistryFragment()
            val bundle = Bundle()
            bundle.putString(KEY_KEY, str)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_registry, container, false)
        ButterKnife.bind(this, view)
        goToSignIn.setOnClickListener {
            goToSignIn()
        }
        SignUpButton.setOnClickListener{
            onClickRegistryButton()
        }
        return view
    }

    override fun progressBarVisibility(b: Boolean) {
        registryProgressBar.visibility = if (b) View.VISIBLE else View.GONE
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Log.d(TAG, "onConnectionFailed:$connectionResult")
    }

    private fun onClickRegistryButton(){
        val name= signInEditName.text.toString()
        val surname = signUpEditSurname.text.toString()
        val email = signUpEditEmail.text.toString()
        val password = signUpEditPassword.text.toString()
        validateData()
        presenter.onRegistryPress(email, password, name, surname)
    }

    fun goToSignIn() {
        val loginFragment = LoginFragment.newInstance("")
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.entryContainer, loginFragment)
        transaction?.commit()
    }

    override fun goToRegistryDetails() {
        val registryDetailsFragment = RegistryDetailsFragment()
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.entryContainer, registryDetailsFragment)
        transaction?.commit()
    }

    override fun logger(message: String, debugLevel: RegistryPresenter.DebugLevel) {
        when (debugLevel) {
            RegistryPresenter.DebugLevel.DEBUG -> Log.d(AuthActivity.TAG, message)
            RegistryPresenter.DebugLevel.ERROR -> Log.e(AuthActivity.TAG, message)
        }
    }

    private fun validateData():Boolean {

        val nameIsEmpty =  getString(R.string.NameIsEmpty)
        val surnameIsEmpty =  getString(R.string.SurnameIsEmpty)
        val emailIsEmpty =  getString(R.string.emailIsEmpty)
        val emailIsNotContainSymbol =  getString(R.string.emailIsNotContainSymbol)
        val passwordIsEmpty =  getString(R.string.passwordIsEmpty)
        val invalidLengthPassword =  getString(R.string.invalidLengthOfPassword)

        //validate of name
        var result = true
        val name = signInEditName.text.toString()
        if (name.isEmpty()) {
            signInEditName.error = nameIsEmpty
            result = false
        }
        else
            layout_signInEditName.isErrorEnabled = false

        //validate of surname
        val surname = signUpEditSurname.text.toString()
        if (surname.isEmpty()) {
            signUpEditSurname.error = surnameIsEmpty
            result = false
        }
        else
            layout_signUpEditSurname.isErrorEnabled = false

        //validate of email
        val email = signUpEditEmail.text.toString()
        if (email.isEmpty()) {
            signUpEditEmail.error = emailIsEmpty
            result = false
        }
        else if (!email.contains("@", true)) {
            signUpEditEmail.error =  emailIsNotContainSymbol
            result = false
        }

        //validate of password
        val password =  signUpEditPassword.text.toString()
        if( password.isEmpty()){
            signUpEditPassword.error =  passwordIsEmpty
            layout_signUpEditPassword.isErrorEnabled = true
            result = false
        }
        else if(password.length in 11 downTo 2){
            signUpEditPassword.error = invalidLengthPassword
            layout_signUpEditPassword.isErrorEnabled = true
            result = false
        }
        return result
    }
}

