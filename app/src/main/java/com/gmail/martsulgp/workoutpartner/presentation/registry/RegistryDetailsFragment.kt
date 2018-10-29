package com.gmail.martsulgp.workoutpartner.presentation.registry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.arellomobile.mvp.MvpAppCompatFragment
import com.gmail.martsulgp.workoutpartner.R

class RegistryDetailsFragment : MvpAppCompatFragment(), RegistryDetailsView {

    override fun progressBarVisibility(b: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_registry_details, container, false)
        ButterKnife.bind(this, view)
        return view
    }
}