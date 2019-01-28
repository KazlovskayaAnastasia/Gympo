package com.gmail.martsulgp.gympo.presentation.menu.profileMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.gmail.martsulgp.gympo.R
import com.gmail.martsulgp.gympo.data.model.entity.UserData
import java.io.Serializable

class ProfileChangeRadioBtnFragment: BaseProfileChangeFragment() {

    @BindView(R.id.profile_radioBtn_info)
    lateinit var fieldHint: TextView

    @BindView(R.id.change_radioBtn)
    lateinit var radioGroup: RadioGroup

    @BindView(R.id.radioBtn)
    lateinit var radioBtn: RadioButton

    @BindView(R.id.radioBtn1)
    lateinit var radioBtn1: RadioButton

    @BindView(R.id.radioBtn2)
    lateinit var radioBtn2: RadioButton

    @BindView(R.id.radioBtn3)
    lateinit var radioBtn3: RadioButton

    private var userMap = mutableMapOf<String, String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.item_profile_change_radiogroup, container, false)
        ButterKnife.bind(this, view)

        initMap(this.arguments.getSerializable(USER_KEY) as UserData)
        val hint = this.arguments.getString(PARAM_KEY)
        fieldHint.text = hint

        if (hint == "experience"){
            radioBtn.text = resources.getStringArray(R.array.experience)[0]
            radioBtn1.text = resources.getStringArray(R.array.experience)[1]
            radioBtn2.text = resources.getStringArray(R.array.experience)[2]
            radioBtn3.text = resources.getStringArray(R.array.experience)[3]
        } else {
            radioBtn.text = resources.getStringArray(R.array.aim)[0]
            radioBtn1.text = resources.getStringArray(R.array.aim)[1]
            radioBtn2.text = resources.getStringArray(R.array.aim)[2]
            radioBtn3.text = resources.getStringArray(R.array.aim)[3]
        }

        return view
    }

    private fun initMap(userData: UserData) {
        userMap[resources.getStringArray(R.array.profileFields)[5]] = userData.aim.toString()
        userMap[resources.getStringArray(R.array.profileFields)[6]] = userData.experience.toString()

    }

    companion object {
        fun newInstance(param: String, currentUser: Serializable?): ProfileChangeRadioBtnFragment {
            val fragment = ProfileChangeRadioBtnFragment()
            val bundle = Bundle().apply {
                putSerializable(USER_KEY, currentUser)
                putString(PARAM_KEY, param)
            }
            fragment.arguments = bundle
            return fragment
        }
    }
}