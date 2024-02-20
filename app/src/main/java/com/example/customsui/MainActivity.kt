package com.example.customsui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.customsui.bottomsheet.CustomBottomSheet
import com.example.customsui.dialog.CustomDialog
import com.example.customsui.dialog.CustomDialogInterface
import com.example.customsui.extensions.ButtonStyled
import com.example.customsui.extensions.getContextCompatColor
import com.google.android.material.bottomsheet.BottomSheetBehavior


class MainActivity : AppCompatActivity(), CustomDialogInterface {

    private lateinit var dialog: CustomDialog
    private lateinit var customBottomSheet: CustomBottomSheet

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dialogBtn = findViewById<AppCompatButton>(R.id.btnDialog)
        dialogBtn.setOnClickListener {
            dialog = CustomDialog(
                title = "My dialog",
                message = "My message",

                positiveText = "Positive btn",
                positiveClick = this.onPositiveClick(),
                positiveBgColor = getContextCompatColor(R.color.teal_200),
                positiveButtonStyle = ButtonStyled.FILLED,

                negativeText = "Negative btn",
                negativeClick = this.onNegativeClick(),
                negativeBgColor = getContextCompatColor(R.color.teal_200),
                negativeButtonStyle = ButtonStyled.STROKED,

                isDialogCancelable = false
            )
            dialog.show(supportFragmentManager, "myDialog")
        }

        val bottomSheet = findViewById<ConstraintLayout>(R.id.bottomSheet)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // handle onSlide
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_COLLAPSED -> Toast.makeText(this@MainActivity, "STATE_COLLAPSED", Toast.LENGTH_SHORT).show()
                    BottomSheetBehavior.STATE_EXPANDED -> Toast.makeText(this@MainActivity, "STATE_EXPANDED", Toast.LENGTH_SHORT).show()
                    BottomSheetBehavior.STATE_DRAGGING -> Toast.makeText(this@MainActivity, "STATE_DRAGGING", Toast.LENGTH_SHORT).show()
                    BottomSheetBehavior.STATE_SETTLING -> Toast.makeText(this@MainActivity, "STATE_SETTLING", Toast.LENGTH_SHORT).show()
                    BottomSheetBehavior.STATE_HIDDEN -> Toast.makeText(this@MainActivity, "STATE_HIDDEN", Toast.LENGTH_SHORT).show()
                    else -> Toast.makeText(this@MainActivity, "OTHER_STATE", Toast.LENGTH_SHORT).show()
                }
            }
        })

        val bottomSheetPersistentBtn = findViewById<AppCompatButton>(R.id.btnBottomSheetPersistent)
        bottomSheetPersistentBtn.setOnClickListener {
            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED)
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            else
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }


        val bottomSheetModalBtn = findViewById<AppCompatButton>(R.id.btnBottomSheetModal).apply {
            setOnClickListener {
                customBottomSheet = CustomBottomSheet(
                    title = "my BottomSheet",
                    message = "message inside bottomsheet",

//                    layout = R.layout.custom_bottomsheet_main,

                    positiveText = "Positive btn",
                    positiveClick = onPositiveClickBottomSheet(),
                    positiveBgColor = getContextCompatColor(R.color.teal_200),
                    positiveButtonStyle = ButtonStyled.FILLED,

                    negativeText = "Negative btn",
                    negativeClick = onNegativeClickBottomSheet(),
                    negativeBgColor = getContextCompatColor(R.color.teal_200),
                    negativeButtonStyle = ButtonStyled.STROKED,
                )
                customBottomSheet.show(supportFragmentManager, CustomBottomSheet.TAG)
            }
        }
    }

    override fun onPositiveClick() = View.OnClickListener {
//        Toast.makeText(this, "positiveClick", Toast.LENGTH_SHORT).show()
        dialog.dismiss()
    }

    override fun onNegativeClick() = View.OnClickListener {
        Toast.makeText(this, "negativeClick", Toast.LENGTH_SHORT).show()
    }

    private fun onPositiveClickBottomSheet() = View.OnClickListener {
        Toast.makeText(this, "onPositiveClickBottomSheet", Toast.LENGTH_SHORT).show()
    }

    private fun onNegativeClickBottomSheet() = View.OnClickListener {
        Toast.makeText(this, "onNegativeClickBottomSheet", Toast.LENGTH_SHORT).show()
        customBottomSheet.dismiss()
    }
}