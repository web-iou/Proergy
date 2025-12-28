package com.proergy.smart.ui.components

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.proergy.smart.R
import com.proergy.smart.utils.dp

class UIEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.editTextStyle
) : AppCompatEditText(context, attrs, defStyleAttr) {

    init {
        setPadding(20.dp,0,20.dp,0)
        background = GradientDrawable().apply {
            cornerRadius = 100.dp.toFloat()
            setColor(ContextCompat.getColor(context, R.color.fill2))
        }
        textSize=15f
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
    }
}
