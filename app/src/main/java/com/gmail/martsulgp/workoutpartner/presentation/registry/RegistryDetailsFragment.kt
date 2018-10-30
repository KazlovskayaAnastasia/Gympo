package com.gmail.martsulgp.workoutpartner.presentation.registry

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.arellomobile.mvp.MvpAppCompatFragment
import com.gmail.martsulgp.workoutpartner.R
import android.widget.EditText



class RegistryDetailsFragment : MvpAppCompatFragment(), RegistryDetailsView {

    @BindView(R.id.layout_authEditName)
    lateinit var layout_signInEditName: TextInputLayout
    @BindView(R.id.signInEditName)
    lateinit var signInEditName: TextInputEditText
    @BindView(R.id.layout_authEditSurname)
    lateinit var layout_signInEditSurname: TextInputLayout
    @BindView(R.id.signInEditSurname)
    lateinit var signInEditSurname: TextInputEditText
    @BindView(R.id.layout_editHeight)
    lateinit var layout_editHeight: TextInputLayout
    @BindView(R.id.signInEditHeight)
    lateinit var signInEditHeight: TextInputEditText
    @BindView(R.id.layout_editWeight)
    lateinit var layout_editWeight: TextInputLayout
    @BindView(R.id.signInEditWeight)
    lateinit var signInEditWeight: TextInputEditText
    @BindView(R.id.btn_ok)
    lateinit var btnOk: Button

    override fun progressBarVisibility(b: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_registry_details, container, false)
        ButterKnife.bind(this, view)

        val validationFields = object : ArrayList<TextInputEditText>() {
            init {
                add(signInEditName)
                add(signInEditSurname)
                add(signInEditHeight)
                add(signInEditWeight)
            }
        }

        btnOk.setOnClickListener {
            for (i in validationFields){
                if (i.text.toString().isEmpty()){
                    i.error = "This field shouldn't be empty"
                }
            }
        }

        return view
    }
}