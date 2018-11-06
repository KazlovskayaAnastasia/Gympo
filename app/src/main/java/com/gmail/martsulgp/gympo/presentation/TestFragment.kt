package com.gmail.martsulgp.gympo.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import butterknife.BindView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.gmail.martsulgp.gympo.R

class TestFragment : MvpAppCompatFragment(),  View.OnClickListener {

    override fun onClick(v: View?) {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.test_fgragment, container, false)
        return view
    }

    @BindView(R.id.signInButton)
    lateinit var signInButton: Button



}

