package com.gmail.martsulgp.gympo.presentation.menu.profileMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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
import org.koin.android.ext.android.inject

class ProfileFragment : MvpFragment(), ProfileView {

    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var presenter: ProfilePresenter
    private val userDataRepository: UserDataRepository by inject()

    @ProvidePresenterTag(presenterClass = ProfilePresenter::class, type = PresenterType.GLOBAL)
    fun provideDialogPresenterTag(): String = "Profile"

    @ProvidePresenter(type = PresenterType.GLOBAL)
    fun provideDialogPresenter() = ProfilePresenter(userDataRepository)

    @BindView(R.id.profile_user_pic)
    lateinit var profile_user_pic: ImageView

    @BindView(R.id.profile_user_name)
    lateinit var profile_user_name: TextView

    @BindView(R.id.profile_user_surname)
    lateinit var profile_user_surname: TextView

    @BindView(R.id.profile_user_age)
    lateinit var profile_user_age: TextView

    @BindView(R.id.profile_user_height)
    lateinit var profile_user_height: TextView

    @BindView(R.id.profile_user_weight)
    lateinit var profile_user_weight: TextView

    @BindView(R.id.profile_user_experience)
    lateinit var profile_user_experience: TextView

    @BindView(R.id.profile_user_aim)
    lateinit var profile_user_aim: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.profile_fragment, container, false)
        ButterKnife.bind(this, view)
        presenter.getUserInfo()

        return view
    }

    override fun updateData(userData: UserData) {
        profile_user_name.text = userData.name
        profile_user_surname.text = userData.surname
        profile_user_age.text = userData.age.toString()
        profile_user_weight.text = userData.weight.toString()
        profile_user_height.text= userData.height.toString()
        profile_user_experience.text = resources.getStringArray(R.array.period)[userData.experience]
        profile_user_aim.text = resources.getStringArray(R.array.destination)[userData.aim]

    }
}