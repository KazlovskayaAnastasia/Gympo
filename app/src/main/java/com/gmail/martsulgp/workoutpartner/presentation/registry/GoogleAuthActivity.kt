package com.gmail.martsulgp.workoutpartner.presentation.registry

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.TextView
import com.gmail.martsulgp.workoutpartner.R
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.GoogleApiClient

@SuppressLint("Registered")
class GoogleAuthActivity: AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private var googleApiClient: GoogleApiClient? = null
    private var statusTextView: TextView? = null
    private var signInButton: SignInButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_registry)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        googleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()

        signInButton = findViewById(R.id.sign_in_button)
        signInButton!!.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        signIn()
    }

    private fun signIn() {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
        startActivityForResult(signInIntent, REQEST_SIGN_IN)
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Log.d(TAG, "onConnectionFailed:$connectionResult")
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQEST_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleSignInResult(result)
        }
    }

    private fun handleSignInResult(result: GoogleSignInResult) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess)
        if (result.isSuccess) {
            val account = result.signInAccount
            statusTextView!!.text = "Signed" + account!!.email!!
            signInButton!!.visibility = View.GONE
        } else {
            statusTextView!!.text = "Unsuccessfully"
        }
    }

    companion object {
         const val REQEST_SIGN_IN = 9001
         const val TAG = "Google_Sign_In"
    }
}
