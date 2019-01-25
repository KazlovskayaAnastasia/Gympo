package com.gmail.martsulgp.gympo.presentation.menu.profileMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.gmail.martsulgp.gympo.R
import com.gmail.martsulgp.gympo.data.model.entity.UserData
import java.io.Serializable

class ProfileChangeTextFragment: BaseProfileChangeFragment() {

    @BindView(R.id.profile_user_hint_info)
    lateinit var fieldHint: TextView

    @BindView(R.id.profile_user_change_info)
    lateinit var fieldContent: EditText

    private var userMap = mutableMapOf<String, String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.item_profile_change_text, container, false)
        ButterKnife.bind(this, view)

        initMap(this.arguments.getSerializable(USER_KEY) as UserData)
        val hint = this.arguments.getString(PARAM_KEY)
        fieldHint.text = hint
        fieldContent.setText(userMap[hint!!])

        return view
    }

    private fun initMap(userData: UserData) {
        userMap[resources.getStringArray(R.array.profileFields)[0]] = userData.name
        userMap[resources.getStringArray(R.array.profileFields)[1]] = userData.surname
        userMap[resources.getStringArray(R.array.profileFields)[2]] = userData.age.toString()
        userMap[resources.getStringArray(R.array.profileFields)[3]] = userData.height.toString()
        userMap[resources.getStringArray(R.array.profileFields)[4]] = userData.weight.toString()
        userMap[resources.getStringArray(R.array.profileFields)[5]] = userData.aim.toString()
        userMap[resources.getStringArray(R.array.profileFields)[6]] = userData.experience.toString()
    }


    companion object {
        fun newInstance(param: String, currentUser: Serializable?): ProfileChangeTextFragment {
            val fragment = ProfileChangeTextFragment()
            val bundle = Bundle().apply {
                putSerializable(USER_KEY, currentUser)
                putString(PARAM_KEY, param)
            }
            fragment.arguments = bundle
            return fragment
        }
    }
}