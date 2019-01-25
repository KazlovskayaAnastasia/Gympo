package com.gmail.martsulgp.gympo.presentation.menu.profileMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import com.arellomobile.mvp.MvpFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.arellomobile.mvp.presenter.ProvidePresenterTag
import com.gmail.martsulgp.gympo.R
import com.gmail.martsulgp.gympo.data.model.entity.UserData
import java.io.Serializable

open class BaseProfileChangeFragment : MvpFragment(), BaseProfileChangeView {

    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var presenter: BaseProfileChangePresenter

    @ProvidePresenterTag(presenterClass = BaseProfileChangePresenter::class, type = PresenterType.GLOBAL)
    fun provideDialogPresenterTag(): String = "BaseProfileChange"

    @ProvidePresenter(type = PresenterType.GLOBAL)
    fun provideDialogPresenter() = BaseProfileChangePresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.base_profile_change_fragment, container, false)
        ButterKnife.bind(this, view)

        (activity as AppCompatActivity).supportActionBar!!.hide()
        addFragment(
                when (this.arguments.getString(FRAGMENT_KEY, "")) {
                    RADIO_BTN_EDIT_KEY -> EditorEnum.RADIOBTN_EDIT
                    else -> EditorEnum.TEXT_EDIT
                },
                this.arguments.getString(PARAM_KEY, ""),
                this.arguments.getSerializable(USER_KEY))
        return view
    }

    companion object {
        const val TEXT_EDIT_KEY = "Text edit fragment"
        const val RADIO_BTN_EDIT_KEY = "Radio edit fragment"
        const val FRAGMENT_KEY = "Fragment editor"
        const val PARAM_KEY = "Param descriptor"
        const val USER_KEY = "User descriptor"


        fun editField(enum: EditorEnum, param: String, currentUser: UserData): BaseProfileChangeFragment {
            var fragment = BaseProfileChangeFragment()
            var bundle = Bundle().apply {
                when (enum) {
                    EditorEnum.TEXT_EDIT -> putString(FRAGMENT_KEY, TEXT_EDIT_KEY)
                    EditorEnum.RADIOBTN_EDIT -> putString(FRAGMENT_KEY, RADIO_BTN_EDIT_KEY)
                }

                putString(PARAM_KEY, param)
                putSerializable(USER_KEY, currentUser)
            }
            fragment.arguments = bundle
            return fragment
        }

        fun selectField() {

        }
    }

    private fun addFragment(type: EditorEnum, param: String, currentUser: Serializable?) {

        val editFragment = when(type) {
            EditorEnum.RADIOBTN_EDIT -> ProfileChangeRadioBtnFragment()
            EditorEnum.TEXT_EDIT -> ProfileChangeTextFragment.newInstance(param,currentUser)
        }
        val transaction = fragmentManager?.beginTransaction()
        transaction?.add(R.id.edit_profile_container, editFragment)
        transaction?.commit()
    }


    enum class EditorEnum {
        TEXT_EDIT,
        RADIOBTN_EDIT
    }
}