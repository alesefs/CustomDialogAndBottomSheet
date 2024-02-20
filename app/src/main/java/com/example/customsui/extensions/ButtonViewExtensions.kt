package com.example.customsui.extensions

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.widget.Button
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.customsui.R


fun Context.getDimensions(@DimenRes dimenId: Int) = this.resources.getDimensionPixelSize(dimenId)

fun Context.getContextCompatColor(@ColorRes colorId: Int) = ContextCompat.getColor(this, colorId)

fun Context.getContextCompatDrawable(@DrawableRes drawableId: Int) = ContextCompat.getDrawable(this, drawableId)

fun Context.getContextCompatFont(@FontRes fontId: Int) = ResourcesCompat.getFont(this, fontId)

/*fun Context.setThemeColor(color: Int) {
    val hexColor = color.toEquivalentColor()
    setTheme(resources.getIdentifier(COLOR_FORMAT.format(hexColor), STYLE, packageName))
}

fun Int.toEquivalentColor(): String {
    val ALPHA_INDEX = 2
    val HASH = "#"
    val EMPTY = ""
    val START_INDEX = 0
    val END_INDEX = 256
    val STEP = 15

    val color = Integer.toHexString(this).substring(ALPHA_INDEX).replace(HASH, EMPTY)

    var red = EMPTY
    var blue = EMPTY
    var green = EMPTY

    for(i in START_INDEX until END_INDEX step STEP) {
        if ()
    }

    return "${toHex(red)}${toHex(green)}${toHex(blue)}"
}*/

fun Button.setFilledColor(color: Int) {
    with(context) {
        val button = GradientDrawable()
        with(button) {
            shape = GradientDrawable.RECTANGLE
            setColor(color)
            cornerRadius = getDimensions(R.dimen.value_24).toFloat()
            this@setFilledColor.setTextColor(getContextCompatColor(R.color.white))
            this@setFilledColor.background = button
        }
    }
}

fun Button.setStrokedColor(color: Int) {
    with(context) {
        val button = GradientDrawable()
        with(button) {
            shape = GradientDrawable.RECTANGLE
            setColor(getContextCompatColor(R.color.white))
            cornerRadius = getDimensions(R.dimen.value_24).toFloat()
            setStroke(getDimensions(R.dimen.value_2), color)
            this@setStrokedColor.setTextColor(color)
            this@setStrokedColor.background = button
        }
    }
}

private fun linearGradientDrawable(highlight: Pair<Int, Int>, context: Context) : GradientDrawable{
    return GradientDrawable().apply {
        colors = intArrayOf(
            highlight.first,
            highlight.second
        )
        gradientType = GradientDrawable.LINEAR_GRADIENT
        shape = GradientDrawable.RECTANGLE
        orientation = GradientDrawable.Orientation.BOTTOM_TOP

        // border around drawable
        setStroke(
            context.getDimensions(R.dimen.value_2),
            context.getContextCompatColor(R.color.white)
        )
    }
}

fun Button.setHighlightColorButton(highlight: Pair<Int, Int>) {
    with(context) {
//        val button = GradientDrawable()
        val button = linearGradientDrawable(Pair(highlight.first, highlight.second), context)
        with(button) {
            shape = GradientDrawable.RECTANGLE
//            setColor(highlight.first)
            cornerRadius = getDimensions(R.dimen.value_24).toFloat()
//            setStroke(getDimensions(R.dimen.value_2), highlight.second)
            this@setHighlightColorButton.setTextColor(getContextCompatColor(R.color.white))
            this@setHighlightColorButton.background = button
        }
    }
}

fun Button.setLayoutParams(layout: ConstraintLayout.LayoutParams, id: Int) {
    with(context) {
        layout.topToBottom = id
        layout.topMargin = getDimensions(R.dimen.value_64)
    }
}