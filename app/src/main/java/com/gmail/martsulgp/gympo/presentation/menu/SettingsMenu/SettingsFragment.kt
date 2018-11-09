package com.gmail.martsulgp.gympo.presentation.menu.SettingsMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpFragment
import com.gmail.martsulgp.gympo.R

class SettingsFragment : MvpFragment(), SettingsView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.settings_fragment, container, false)
        return view
    }
}