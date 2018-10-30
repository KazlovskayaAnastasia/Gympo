package com.gmail.martsulgp.workoutpartner.presentation.registry

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.app.FragmentActivity
import android.util.Log
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
import com.arellomobile.mvp.presenter.ProvidePresenterTag
import com.gmail.martsulgp.workoutpartner.R
import com.gmail.martsulgp.workoutpartner.data.repository.UserDataRepository
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.GoogleApiClient
import org.koin.android.ext.android.inject

class RegistryFragment : MvpAppCompatFragment(), GoogleApiClient.OnConnectionFailedListener, View.OnClickListener, RegistryView {

    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var presenter: RegistryPresenter
    private var googleApiClient: GoogleApiClient? = null
    private val userDataRepository: UserDataRepository by inject()
    private var signInButton: SignInButton? = null

    @ProvidePresenterTag(presenterClass = RegistryPresenter::class, type = PresenterType.GLOBAL)
    fun provideDialogPresenterTag(): String = "Registry"

    @ProvidePresenter(type = PresenterType.GLOBAL)
    fun provideDialogPresenter() = RegistryPresenter(userDataRepository)

    @BindView(R.id.layout_signInEditName)
    lateinit var layout_signInEditName: TextInputLayout
    @BindView(R.id.signInEditName)
    lateinit var signInEditName: TextInputEditText

    @BindView(R.id.layout_signUpEditSurname)
    lateinit var layout_signUpEditSurname: TextInputLayout
    @BindView(R.id.signUpEditSurname)
    lateinit var signUpEditSurname: TextInputEditText

    @BindView(R.id.layout_signUpEditEmail)
    lateinit var layout_signUpEditEmail: TextInputLayout
    @BindView(R.id.signUpEditEmail)
    lateinit var signUpEditEmail: TextInputEditText

    @BindView(R.id.layout_signUpEditPassword)
    lateinit var layout_signUpEditPassword: TextInputLayout
    @BindView(R.id.signUpEditPassword)
    lateinit var signUpEditPassword: TextInputEditText

    @BindView(R.id.sign_in_button)
    lateinit var sign_in_button: SignInButton

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
        presenter.onPageLoaded()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        googleApiClient = GoogleApiClient.Builder(this.context!!)
                .enableAutoManage(FragmentActivity(), this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()

        sign_in_button.setOnClickListener {
            signIn()
        }
        return view
    }

    override fun progressBarVisibility(b: Boolean) {
        registryProgressBar.visibility = if (b) View.VISIBLE else View.GONE
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Log.d(TAG, "onConnectionFailed:$connectionResult")
    }

    override fun signIn() {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
        startActivityForResult(signInIntent, REQEST_SIGN_IN)
    }

    override fun logger(message: String, debugLevel: RegistryPresenter.DebugLevel) {
        when (debugLevel) {
            RegistryPresenter.DebugLevel.DEBUG -> Log.d(RegistryActivity.TAG, message)
            RegistryPresenter.DebugLevel.ERROR -> Log.e(RegistryActivity.TAG, message)
        }
    }

    override fun onClick(view: View) {
        signIn()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQEST_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleSignInResult(result)
        }
    }

    override fun handleSignInResult(result: GoogleSignInResult) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess)
    }
}

