package com.gmail.martsulgp.gympo.presentation.menu.statisticMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpFragment
import com.gmail.martsulgp.gympo.R

class StatisticFragment : MvpFragment(), StatisticView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.statistic_fragment, container, false)
        return view
    }
}