package com.gmail.martsulgp.gympo.presentation.login

import android.content.Intent
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
import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.gmail.martsulgp.gympo.R
import com.gmail.martsulgp.gympo.data.repository.UserDataRepository
import com.gmail.martsulgp.gympo.presentation.TestFragment
import com.gmail.martsulgp.gympo.presentation.registry.RegistryFragment
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.GoogleApiClient
import org.json.JSONException
import org.koin.android.ext.android.inject
import java.util.*

class LoginFragment : MvpFragment(), GoogleApiClient.OnConnectionFailedListener, LoginView {

    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var presenter: LoginPresenter
    lateinit var fbProfile: Profile
    lateinit var tracker: ProfileTracker

    private var googleApiClient: GoogleApiClient? = null
    private val userDataRepository: UserDataRepository by inject()
    private var callbackManager: CallbackManager? = null
    private val accessTokenTracker: AccessTokenTracker? = null
    @ProvidePresenterTag(presenterClass = LoginPresenter::class, type = PresenterType.GLOBAL)
    fun provideDialogPresenterTag(): String = "Login"

    @ProvidePresenter(type = PresenterType.GLOBAL)
    fun provideDialogPresenter() = LoginPresenter(userDataRepository)

    @BindView(R.id.layout_signInEditLogin)
    lateinit var layout_signInEditLogin: com.google.android.material.textfield.TextInputLayout

    @BindView(R.id.signInEditLogin)
    lateinit var signInEditLogin: com.google.android.material.textfield.TextInputEditText
    @BindView(R.id.layout_signInEditPassword)
    lateinit var layout_signInEditPassword: com.google.android.material.textfield.TextInputLayout

    @BindView(R.id.signInEditPassword)
    lateinit var signInEditPassword: com.google.android.material.textfield.TextInputEditText
    @BindView(R.id.sign_in_button)
    lateinit var sign_in_button: SignInButton

    @BindView(R.id.facebook_login_Button)
    lateinit var loginButton: LoginButton

    @BindView(R.id.signInButton)
    lateinit var signInButton: Button

    @BindView(R.id.goToSignUp)
    lateinit var goToSignUp: TextView

    @BindView(R.id.loginProgressBar)
    lateinit var loginProgressBar: ProgressBar

    companion object {
        private const val KEY_KEY = "KEY"
        const val REQEST_SIGN_IN = 9001
        const val TAG = "Google_Sign_In"
        fun newInstance(str: String): LoginFragment {
            val fragment = LoginFragment()
            val bundle = Bundle()
            bundle.putString(KEY_KEY, str)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        FacebookSdk.sdkInitialize(this.context)
        AppEventsLogger.activateApp(this.context)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        ButterKnife.bind(this, view)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
// Todo reveal the issue with ".enableAutoManage" later
        googleApiClient = GoogleApiClient.Builder(this.context!!)
//                .enableAutoManage(FragmentActivity(), this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()

        sign_in_button.setOnClickListener {
            signIn()
        }

        goToSignUp.setOnClickListener {
            goToSignUp()
        }

        signInButton.setOnClickListener {
            onClickLoginButton()
        }

// Todo reveal the issue with "support.v4" later
        AppEventsLogger.activateApp(this.context)
        callbackManager = CallbackManager.Factory.create()
        loginButton.setReadPermissions(Arrays.asList("public_profile", "user_birthday", "user_gender"))
//        loginButton.fragment = this
        loginButton.registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {

                    override fun onSuccess(loginResult: LoginResult) {
                        if(Profile.getCurrentProfile() == null){
                            tracker = object : ProfileTracker(){
                                override fun onCurrentProfileChanged(oldProfile: Profile?, currentProfile: Profile?) {
                                    tracker.stopTracking()
                                }
                            }
                        } else {
                            fbProfile = Profile.getCurrentProfile()
                        }

//  TODO not sure whether to use or not
                        val request = GraphRequest.newMeRequest(
                                loginResult.accessToken
                        ) { `object`, response ->
                            try {
                                val email = `object`.getString("email")
                                Log.d("Email = ", " $email")
                                val birthday = `object`.getString("birthday")
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }
                        }
                        val parameters = Bundle()
                        parameters.putString("fields", "id, name, email, gender, birthday")
                        request.parameters = parameters
                        request.executeAsync()
//  TODO
                        if(!request.accessToken.token.isNullOrBlank())
                            presenter.exchangeWithBackendless(request.accessToken.token)
                    }
                    override fun onCancel() {}
                    override fun onError(error: FacebookException) {}
                })
        return view
    }

    //TODO delete later if not needed
//    private fun goMainScreen() {
//        if (Profile.getCurrentProfile() != null) {
//            val profile = Profile.getCurrentProfile()
//            val intent = Intent(this.context, LoginActivity::class.java)
//            intent.putExtra("name", profile.firstName)
//            intent.putExtra("id", profile.id)
//            startActivity(intent)
//        } else {
//            val profileTracker = object : ProfileTracker() {
//                override fun onCurrentProfileChanged(oldProfile: Profile?, currentProfile: Profile) {
//                    stopTracking()
//                    Profile.setCurrentProfile(currentProfile)
//                }
//            }
//            profileTracker.startTracking()
//        }
//    }

    override fun progressBarVisibility(b: Boolean) {
        loginProgressBar.visibility = if (b) View.VISIBLE else View.GONE
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Log.d(TAG, "onConnectionFailed:$connectionResult")
    }

    // Google
    override fun signIn() {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
        startActivityForResult(signInIntent, REQEST_SIGN_IN)
    }

    override fun logger(message: String, debugLevel: LoginPresenter.DebugLevel) {
        when (debugLevel) {
            LoginPresenter.DebugLevel.DEBUG -> Log.d(LoginActivity.TAG, message)
            LoginPresenter.DebugLevel.ERROR -> Log.e(LoginActivity.TAG, message)
        }
    }

    override fun updateFbUser(){
        presenter.updateUserData(fbProfile)
    }

    //Backendless
    private fun onClickLoginButton(){
        val email= signInEditLogin.text.toString()
        val password = signInEditPassword.text.toString()
        presenter.onLoginPress(email,password)
    }

    //Navigation
    fun goToSignUp() {
        val registryFragment = RegistryFragment()
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.entryContainer, registryFragment)
        transaction?.commit()
    }

    //Results from Google & Facebook
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager!!.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQEST_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleSignInResult(result)
            val testFragment = TestFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.entryContainer, testFragment)
            transaction?.commit()
        }
    }

    override fun handleSignInResult(result: GoogleSignInResult) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess)
    }
}

