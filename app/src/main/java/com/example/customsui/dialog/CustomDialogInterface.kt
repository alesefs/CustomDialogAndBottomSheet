package com.example.customsui.dialog

import android.content.DialogInterface
import android.view.View

interface CustomDialogInterface {
    fun onPositiveClick(): View.OnClickListener
    fun onNegativeClick(): View.OnClickListener
}