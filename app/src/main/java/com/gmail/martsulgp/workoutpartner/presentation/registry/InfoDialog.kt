package com.gmail.martsulgp.workoutpartner.presentation.registry

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import com.gmail.martsulgp.workoutpartner.R
import java.io.Serializable

open class InfoDialog : DialogFragment() {
    companion object {
        private const val KEY_DIALOG_VO = "KEY_DIALOG_VO"

        fun getOkButton(context: Context) = DialogButton(ButtonType.POSITIVE, context.getString(R.string.ok).toUpperCase(), {})
        fun getYesButton(context: Context) = DialogButton(ButtonType.POSITIVE, context.getString(R.string.yes).capitalize(), {})
        fun getNoButton(context: Context) = DialogButton(ButtonType.NEGATIVE, context.getString(R.string.no).capitalize(), {})
        fun getCloseButton(context: Context, function: () -> kotlin.Unit) = DialogButton(ButtonType.NEGATIVE, context.getString(R.string.close).capitalize()) {InfoDialog().onButtonClick(function)}
        fun getCancelButton(context: Context) = DialogButton(ButtonType.NEGATIVE, context.getString(R.string.cancel).capitalize()) {}
        fun getContinueButton(context: Context) = DialogButton(ButtonType.POSITIVE, context.getString(R.string.continue_button).capitalize()) {}
        fun getYesNoButtons(context: Context) = arrayOf(getYesButton(context), getNoButton(context))
        fun getCancelContinueButtons(context: Context) = arrayOf(getCancelButton(context), getContinueButton(context))

        fun newInstance(dialogVO: DialogVO): InfoDialog {
            val dialog = InfoDialog()
            val bundle = Bundle()
            bundle.putSerializable(KEY_DIALOG_VO, dialogVO)
            dialog.arguments = bundle
            return dialog
        }
    }

    private val dialogVO: DialogVO by lazy { arguments?.getSerializable(KEY_DIALOG_VO) as DialogVO }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBuilder = AlertDialog.Builder(activity!!)
        if (dialogVO.title.isNotEmpty()) dialogBuilder.setTitle(dialogVO.title)
        if (dialogVO.message.isNotBlank()) dialogBuilder.setMessage(dialogVO.message)
        addButtons(dialogBuilder, dialogVO.buttons)
        val dialog = dialogBuilder.create()
        dialog.setCanceledOnTouchOutside(dialogVO.cancelable)
        return dialog
    }

    private fun addButtons(dialogBuilder: AlertDialog.Builder, buttons: Array<DialogButton>) {
        for (button in buttons) {
            when (button.type) {
                ButtonType.POSITIVE -> dialogBuilder.setPositiveButton(button.buttonLabel) { _, _ -> onButtonClick(button.action) }
                ButtonType.NEGATIVE -> dialogBuilder.setNegativeButton(button.buttonLabel) { _, _ -> onButtonClick(button.action) }
                ButtonType.NEUTRAL -> dialogBuilder.setNeutralButton(button.buttonLabel) { _, _ -> onButtonClick(button.action) }
            }
        }
    }

    private fun onButtonClick(action: () -> Unit) {
        kotlin.run(action)
    }

    override fun onDismiss(dialog: DialogInterface?) {

    }

    class DialogVO(val title: String = "",
                   val message: String = "",
                   val cancelable: Boolean = true,
                   val buttons: Array<InfoDialog.DialogButton>) : Serializable

    class DialogButton(val type: ButtonType,
                       val buttonLabel: String,
                       val action: () -> Unit) : Serializable

    enum class ButtonType {
        POSITIVE,
        NEGATIVE,
        NEUTRAL
    }
}