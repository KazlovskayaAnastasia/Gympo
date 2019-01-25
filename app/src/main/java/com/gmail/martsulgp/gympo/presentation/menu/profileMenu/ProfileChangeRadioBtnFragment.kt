package com.gmail.martsulgp.gympo.presentation.menu.profileMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.gmail.martsulgp.gympo.R

class ProfileChangeRadioBtnFragment: BaseProfileChangeFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.item_profile_change_radiogroup, container, false)
        ButterKnife.bind(this, view)

        return view
    }
}