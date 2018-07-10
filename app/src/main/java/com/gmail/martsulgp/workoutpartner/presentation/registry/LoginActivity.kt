package com.gmail.martsulgp.workoutpartner.presentation.registry

import android.app.Activity
import android.os.Bundle
import com.gmail.martsulgp.workoutpartner.R

class LoginActivity : Activity() {

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

//    public static void showFragment(FragmentManager fragmentManager, Fragment fragment) {
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.entryContainer, fragment, fragment.getClass().getName());
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//    }

}