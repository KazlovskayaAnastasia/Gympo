package com.gmail.martsulgp.gympo.presentation.menu.profileMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.arellomobile.mvp.MvpFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.arellomobile.mvp.presenter.ProvidePresenterTag
import com.gmail.martsulgp.gympo.R
import com.gmail.martsulgp.gympo.data.model.entity.UserData
import com.gmail.martsulgp.gympo.data.repository.UserDataRepository
import com.gmail.martsulgp.gympo.presentation.menu.profileMenu.BaseProfileChangeFragment.EditorEnum
import kotlinx.android.synthetic.main.profile_fragment.view.*
import org.koin.android.ext.android.inject

class ProfileFragment : MvpFragment(), ProfileView, View.OnClickListener {

    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var presenter: ProfilePresenter
    private val userDataRepository: UserDataRepository by inject()

    @ProvidePresenterTag(presenterClass = ProfilePresenter::class, type = PresenterType.GLOBAL)
    fun provideDialogPresenterTag(): String = "Profile"

    @ProvidePresenter(type = PresenterType.GLOBAL)
    fun provideDialogPresenter() = ProfilePresenter(userDataRepository)

    @BindView(R.id.profile_user_pic)
    lateinit var profile_user_pic: ImageView

    @BindView(R.id.layout_user_name)
    lateinit var layout_user_name: RelativeLayout

    @BindView(R.id.profile_user_name)
    lateinit var profile_user_name: TextView

    @BindView(R.id.layout_user_surname)
    lateinit var layout_user_surname: RelativeLayout

    @BindView(R.id.profile_user_surname)
    lateinit var profile_user_surname: TextView

    @BindView(R.id.layout_user_age)
    lateinit var layout_user_age: RelativeLayout

    @BindView(R.id.profile_user_age)
    lateinit var profile_user_age: TextView

    @BindView(R.id.layout_user_height)
    lateinit var layout_user_height: RelativeLayout

    @BindView(R.id.profile_user_height)
    lateinit var profile_user_height: TextView

    @BindView(R.id.layout_user_weight)
    lateinit var layout_user_weight: RelativeLayout

    @BindView(R.id.profile_user_weight)
    lateinit var profile_user_weight: TextView

    @BindView(R.id.layout_user_experience)
    lateinit var layout_user_experience: RelativeLayout

    @BindView(R.id.profile_user_experience)
    lateinit var profile_user_experience: TextView

    @BindView(R.id.layout_user_aim)
    lateinit var layout_user_aim: RelativeLayout

    @BindView(R.id.profile_user_aim)
    lateinit var profile_user_aim: TextView

    //hints
    @BindView(R.id.profile_user_name_header)
    lateinit var profile_user_name_header: TextView

    @BindView(R.id.profile_user_surname_header)
    lateinit var profile_user_surname_header: TextView

    @BindView(R.id.profile_user_age_header)
    lateinit var profile_user_age_header: TextView

    @BindView(R.id.profile_user_height_header)
    lateinit var profile_user_height_header: TextView

    @BindView(R.id.profile_user_weight_header)
    lateinit var profile_user_weight_header: TextView

    @BindView(R.id.profile_user_experience_header)
    lateinit var profile_user_experience_header: TextView

    @BindView(R.id.profile_user_aim_header)
    lateinit var profile_user_aim_header: TextView

    private lateinit var currentUser: UserData

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.profile_fragment, container, false)
        ButterKnife.bind(this, view)
        presenter.getUserInfo()

        layout_user_name.setOnClickListener(this)
        layout_user_surname.setOnClickListener(this)
        layout_user_age.setOnClickListener(this)
        layout_user_height.setOnClickListener(this)
        layout_user_weight.setOnClickListener(this)
        layout_user_experience.setOnClickListener(this)
        layout_user_aim.setOnClickListener(this)

        profile_user_name_header.text = resources.getStringArray(R.array.profileFields)[0]
        profile_user_surname_header.text = resources.getStringArray(R.array.profileFields)[1]
        profile_user_age_header.text = resources.getStringArray(R.array.profileFields)[2]
        profile_user_height_header.text = resources.getStringArray(R.array.profileFields)[3]
        profile_user_weight_header.text = resources.getStringArray(R.array.profileFields)[4]
        profile_user_aim_header.text = resources.getStringArray(R.array.profileFields)[5]
        profile_user_experience_header.text = resources.getStringArray(R.array.profileFields)[6]

        return view
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.layout_user_name -> goToChangeTextFragment(EditorEnum.TEXT_EDIT, layout_user_name.profile_user_name_header.text.toString())
            R.id.layout_user_surname -> goToChangeTextFragment(EditorEnum.TEXT_EDIT, layout_user_surname.profile_user_surname_header.text.toString())
            R.id.layout_user_age -> goToChangeTextFragment(EditorEnum.TEXT_EDIT, layout_user_age.profile_user_age_header.text.toString())
            R.id.layout_user_height -> Toast.makeText(activity, "Height Pressed!", Toast.LENGTH_SHORT).show()
            R.id.layout_user_weight -> Toast.makeText(activity, "Weight Pressed!", Toast.LENGTH_SHORT).show()
            R.id.layout_user_experience -> Toast.makeText(activity, "Experience Pressed!", Toast.LENGTH_SHORT).show()
            R.id.layout_user_aim -> Toast.makeText(activity, "Aim Pressed!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun updateData(userData: UserData) {
        currentUser = userData
        profile_user_name.text = userData.name
        profile_user_surname.text = userData.surname
        profile_user_age.text = userData.age.toString()
        profile_user_weight.text = userData.weight.toString()
        profile_user_height.text= userData.height.toString()
        profile_user_experience.text = resources.getStringArray(R.array.experience)[userData.experience]
        profile_user_aim.text = resources.getStringArray(R.array.aim)[userData.aim]

    }

    private fun goToChangeTextFragment(enum: EditorEnum, param: String) {
        val profileChangeTextFragment = BaseProfileChangeFragment.editField(enum, param, currentUser)
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.main_fragment_container, profileChangeTextFragment)
        transaction?.commit()
    }
}