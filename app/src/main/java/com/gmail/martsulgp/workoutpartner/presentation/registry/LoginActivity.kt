package com.gmail.martsulgp.workoutpartner.presentation.registry

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.ProgressBar
import butterknife.BindView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.gmail.martsulgp.workoutpartner.R

class LoginActivity : MvpAppCompatActivity(), LoginView {

    override fun progressBarVisibility(b: Boolean) {
        when(b){
            true -> progressBar.visibility = View.VISIBLE
            else -> progressBar.visibility = View.GONE
        }
    }

    @BindView(R.id.entryProgress)
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //TODO add a progressBar in activity xml

//        showProgress(getSupportFragmentManager());
//        tokenUseCase.execute(preferences.getString(TOKEN_NAME, null), new DisposableObserver<Boolean>() {
//            @Override
//            public void onNext(Boolean response) {
//                if (response) {
//                    Intent intent = new Intent(EntryActivity.this, NavigationActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//                tokenUseCase.dispose();
//                removeProgress(getSupportFragmentManager());
//            }
//        });
//
//        showFragment(getSupportFragmentManager(), new LogInFragment().getInstance());
//    }
    }

    val Fragment.TAG: String
        get() = this::class.java.simpleName

    fun showFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        with(supportFragmentManager.findFragmentById(R.id.entryContainer)) {
            if (this != null)
                fragmentTransaction.detach(this)
        }
        if (fragment.isDetached) {
            fragmentTransaction.attach(fragment)
            return
        }
        fragmentTransaction.add(R.id.entryContainer, fragment, fragment.TAG)
        fragmentTransaction.commitAllowingStateLoss()
    }

//    public static void showFragment(FragmentManager fragmentManager, Fragment fragment) {
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.entryContainer, fragment, fragment.getClass().getName());
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//    }

}