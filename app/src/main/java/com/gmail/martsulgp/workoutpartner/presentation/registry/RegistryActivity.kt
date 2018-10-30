package com.gmail.martsulgp.workoutpartner.presentation.registry

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.arellomobile.mvp.presenter.ProvidePresenterTag
import com.gmail.martsulgp.workoutpartner.R
import com.gmail.martsulgp.workoutpartner.data.repository.UserDataRepository
import com.gmail.martsulgp.workoutpartner.presentation.MainActivity
import com.gmail.martsulgp.workoutpartner.presentation.MainPresenter
import com.gmail.martsulgp.workoutpartner.presentation.login.LoginView
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import io.reactivex.observers.DisposableObserver
import org.koin.android.ext.android.inject


class RegistryActivity : MvpAppCompatActivity(), RegistryActivityView {
    override fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.entryContainer, fragment).commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        showFragment(RegistryFragment())



//        showProgress(getSupportFragmentManager())
//        tokenUseCase.execute(preferences.getString(TOKEN_NAME, null), object: DisposableObserver<Boolean>() {
//            fun onNext(response:Boolean) {
//                if (response)
//                {
//                    val intent = Intent(this@EntryActivity, NavigationActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                }
//            }
//            fun onError(e:Throwable) {
//            }
//            fun onComplete() {
//                tokenUseCase.dispose()
//                removeProgress(getSupportFragmentManager())
//            }
//        })
//        showFragment(getSupportFragmentManager(), LogInFragment().getInstance()
    }




//    private fun toSigningIn() {
//        EntryActivity.showFragment(activity.getSupportFragmentManager(), LogInFragment().getInstance())
//    }

companion object {
    const val TAG = "RegistryActivityTag"
}
}