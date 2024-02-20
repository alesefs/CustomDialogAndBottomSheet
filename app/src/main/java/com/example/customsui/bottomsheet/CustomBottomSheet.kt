package com.example.customsui.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.customsui.R
import com.example.customsui.extensions.ButtonStyled
import com.example.customsui.extensions.getContextCompatColor
import com.example.customsui.extensions.setFilledColor
import com.example.customsui.extensions.setStrokedColor
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CustomBottomSheet(
    private val title: String = "",
    private val message: String = "",
//    private val layout: Int? = 0,
    private val positiveText: String? = null,
    private val positiveClick: View.OnClickListener? = null,
    private val positiveBgColor: Int? = 0,
    private val positiveButtonStyle: ButtonStyled? = null,
    private val negativeText: String? = null,
    private val negativeClick: View.OnClickListener? = null,
    private val negativeBgColor: Int? = 0,
    private val negativeButtonStyle: ButtonStyled? = null,
    private val isBottomSheetCancelable: Boolean = false
) : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "CustomBottomSheetDialogFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.custom_bottomsheet_main, container, false)
    }

    override fun getTheme() = R.style.ThemeCustomBottomSheetDialog

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /*this.requireView().findViewById<AppCompatButton?>(R.id.firstButton).apply {
            setOnClickListener {
                Toast.makeText(context, "First Button Clicked", Toast.LENGTH_SHORT).show()
            }
        }

        this.requireView().findViewById<AppCompatButton?>(R.id.secondButton).apply {
            setOnClickListener {
                Toast.makeText(context, "Second Button Clicked", Toast.LENGTH_SHORT).show()
            }
        }

        this.requireView().findViewById<AppCompatButton?>(R.id.thirdButton).apply {
            setOnClickListener {
                Toast.makeText(context, "Third Button Clicked", Toast.LENGTH_SHORT).show()
            }
        }*/

        this.requireView().findViewById<TextView>(R.id.titleBottomSheet).apply {
            text = title
        }

        this.requireView().findViewById<TextView>(R.id.subtitleBottomSheet).apply {
            text = message
        }

        this.requireView().findViewById<AppCompatButton>(R.id.buttonUpBottomSheet).apply {
            text = negativeText

            when (negativeButtonStyle) {
                ButtonStyled.FILLED -> {
                    if (negativeBgColor != null) {
                        setFilledColor(negativeBgColor)
                    } else {
                        setFilledColor(context.getContextCompatColor(com.example.customsui.R.color.primary))
                    }
                }
                ButtonStyled.STROKED -> {
                    if (negativeBgColor != null) {
                        setStrokedColor(negativeBgColor)
                    } else {
                        setStrokedColor(context.getContextCompatColor(com.example.customsui.R.color.primary))
                    }
                }
                else -> {
                    if (negativeBgColor != null && !negativeText.isNullOrEmpty()) {
                        setFilledColor(negativeBgColor)
                    } else if (negativeBgColor == null && !negativeText.isNullOrEmpty()) {
                        setStrokedColor(context.getContextCompatColor(com.example.customsui.R.color.primary))
                    } else {
                        visibility = View.GONE
                    }
                }
            }
            setOnClickListener(negativeClick)
        }

        this.requireView().findViewById<AppCompatButton>(R.id.buttonDownBottomSheet).apply {
            text = positiveText

            when(positiveButtonStyle) {
                ButtonStyled.FILLED -> {
                    if (positiveBgColor != null) {
                        setFilledColor(positiveBgColor)
                    } else {
                        setFilledColor(context.getContextCompatColor(R.color.primary))
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
    }

}