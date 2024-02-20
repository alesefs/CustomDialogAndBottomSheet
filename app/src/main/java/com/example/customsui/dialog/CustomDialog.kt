package com.example.customsui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.customsui.R
import com.example.customsui.extensions.*

class CustomDialog(
    private val title: String = "",
    private val message: String = "",
    private val positiveText: String? = null,
    private val positiveClick: View.OnClickListener? = null,
    private val positiveBgColor: Int? = 0,
    private val positiveButtonStyle: ButtonStyled? = null,
    private val negativeText: String? = null,
    private val negativeClick: View.OnClickListener? = null,
    private val negativeBgColor: Int? = 0,
    private val negativeButtonStyle: ButtonStyled? = null,
    private val isDialogCancelable: Boolean = false
): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val alertDialog = AlertDialog.Builder(it, R.style.ThemeCustomDialog)
            val dialogView: View = LayoutInflater.from(context).inflate(R.layout.custom_dialog_main, null, false)

//            dialogView.background = context.getContextCompatDrawable(R.drawable.button_green)

            val titleDialog: TextView =
                dialogView.findViewById(R.id.titleDialog)
            titleDialog.text = title

            val subtitleDialog: TextView =
                dialogView.findViewById(R.id.subtitleDialog)
            subtitleDialog.text = message

            val myFirstButton: Button =
                dialogView.findViewById(R.id.buttonUpDialog)
            with(myFirstButton) {
                text = negativeText

                when (negativeButtonStyle) {
                    ButtonStyled.FILLED -> {
                        if (negativeBgColor != null) {
                            setFilledColor(negativeBgColor)
                        } else {
                            setFilledColor(context.getContextCompatColor(R.color.primary))
                        }
                    }
                    ButtonStyled.STROKED -> {
                        if (negativeBgColor != null) {
                            setStrokedColor(negativeBgColor)
                        } else {
                            setStrokedColor(context.getContextCompatColor(R.color.primary))
                        }
                    }
                    else -> {
                        if (negativeBgColor != null && !negativeText.isNullOrEmpty()) {
                            setFilledColor(negativeBgColor)
                        } else if (negativeBgColor == null && !negativeText.isNullOrEmpty()) {
                            setStrokedColor(context.getContextCompatColor(R.color.primary))
                        } else {
                            visibility = View.GONE
                        }
                    }
                }
                setOnClickListener(negativeClick)
            }

            val mySecondButton: Button =
                dialogView.findViewById(R.id.buttonDownDialog)
            with(mySecondButton) {
                text = positiveText

                when (positiveButtonStyle) {
                    ButtonStyled.FILLED -> {
                        if (positiveBgColor != null) {
                            setFilledColor(positiveBgColor)
                        } else {
                            setFilledColor(context.getContextCompatColor(R.color.primary))
                            /*setHighlightColorButton(
                                Pair(
                                    context.getContextCompatColor(R.color.primary),
                                    context.getContextCompatColor(R.color.red)
                                )
                            )*/
                        }
                    }
                    ButtonStyled.STROKED -> {
                        if (positiveBgColor != null) {
                            setStrokedColor(positiveBgColor)
                        } else {
                            setStrokedColor(context.getContextCompatColor(R.color.primary))
                        }
                    }
                    else -> {
                        if (positiveBgColor != null && !positiveText.isNullOrEmpty()) {
                            setFilledColor(positiveBgColor)
                        } else if (positiveBgColor == null && !positiveText.isNullOrEmpty()) {
                            setStrokedColor(context.getContextCompatColor(R.color.primary))
                        } else {
                            visibility = View.GONE
                        }
                    }
                }
                setOnClickListener(positiveClick)
            }

            isCancelable = isDialogCancelable

            alertDialog.setView(dialogView)
//                .setTitle(title)
                .create()

        } ?: throw IllegalStateException("Activity is null!!")
    }
}