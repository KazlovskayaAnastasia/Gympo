package com.gmail.martsulgp.workoutpartner.presentation.registry

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import butterknife.BindView
import butterknife.ButterKnife
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.arellomobile.mvp.presenter.ProvidePresenterTag
import com.gmail.martsulgp.workoutpartner.R
import com.gmail.martsulgp.workoutpartner.data.UserDataRepository
import com.gmail.martsulgp.workoutpartner.model.request.UserDataRequest
import org.koin.android.ext.android.inject

class RegistryDetailsFragment : MvpAppCompatFragment(), RegistryDetailsView {

    override fun onSaveClick() {
        var b = false
        for (i in validationFields) {
            if (i.text.toString().isEmpty()) {
                i.error = "This field shouldn't be empty"
                b = true
            }
        }
            presenter.showAlert(b)
        presenter.updateUser(getUser())
    }

     fun getUser()= UserDataRequest(
        name = signInEditName.text.toString(),
        surname = signInEditSurname.text.toString(),
//  TODO on server: age, here: birthday. Fix this issue on backend
        birthday = 21,//1234567L,
        height = signInEditHeight.text.toString().toInt(),
        weight = signInEditWeight.text.toString().toInt(),
        experience = experience.selectedItemPosition,//.toString().toInt(),
        goal = aim.selectedItemPosition//.toString().toInt()
        )

    override fun showAlertDialog(message: String?) {
        InfoDialog.newInstance(InfoDialog.DialogVO(
                message = message ?: "Test message",
                buttons = arrayOf(InfoDialog.getCloseButton(context!!) {
                    Toast.makeText(context, "Close clicked", Toast.LENGTH_SHORT).show()
                },
                        InfoDialog.DialogButton(InfoDialog.ButtonType.POSITIVE, "OK") {
                            presenter.updateUser(getUser())
                        })
        )).show(fragmentManager, "TAG")
    }

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
    @BindView(R.id.calendar)
    lateinit var calendar: CalendarView
    @BindView(R.id.spinner_period)
    lateinit var experience: Spinner
    private var expSelection = 0
    @BindView(R.id.spinner_destination)
    lateinit var aim: Spinner
    private var aimSelection = 0
    @BindView(R.id.btn_ok)
    lateinit var btnOk: Button

    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var presenter: RegistryDetailsPresenter

    private val userDataRepository: UserDataRepository by inject()

    @ProvidePresenterTag(presenterClass = RegistryDetailsPresenter::class, type = PresenterType.GLOBAL)
    fun provideDialogPresenterTag(): String = "Registry"

    @ProvidePresenter(type = PresenterType.GLOBAL)
    fun provideDialogPresenter() = RegistryDetailsPresenter(userDataRepository)

    var validationFields: ArrayList<TextInputEditText> = arrayListOf()

    override fun progressBarVisibility(b: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_registry_details, container, false)
        ButterKnife.bind(this, view)

//        var day = calendar.date.

        experience.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        itemSelected: View, selectedItemPosition: Int, selectedId: Long) {
                expSelection = selectedItemPosition
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                expSelection = 0
            }
        }

        aim.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                aimSelection = 0
            }

            override fun onItemSelected(parent: AdapterView<*>?,
                                        itemSelected: View?, selectedItemPosition: Int, selectedId: Long) {
                aimSelection = selectedItemPosition
            }
        }

        validationFields = object : ArrayList<TextInputEditText>() {
            init {
                add(signInEditName)
                add(signInEditSurname)
                add(signInEditHeight)
                add(signInEditWeight)
            }
        }

        btnOk.setOnClickListener {
            presenter.onSaveBtnClick()
        }

        return view
    }
}