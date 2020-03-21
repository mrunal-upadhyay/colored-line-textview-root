package com.mupadhyay.coloredlinetextview

import android.content.Context
import android.graphics.Canvas
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class ColoredLineTextView : AppCompatTextView {

    private var lineNumber = 0
    private var lineColor = 0

    constructor(context: Context) : super(context) {
        initResources(context, null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initResources(context, attrs)

    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initResources(context, attrs)

    }

    private fun initResources(context: Context?, attrs: AttributeSet?) {
        if (attrs != null) {
            val a = context?.obtainStyledAttributes(attrs, R.styleable.coloredlineAttr)
            lineNumber = a!!.getInt(R.styleable.coloredlineAttr_lineNumber, 0)
            lineColor = a.getColor(R.styleable.coloredlineAttr_lineColor, currentTextColor)
            a.recycle()
        } else {
            lineNumber = 0
            lineColor = currentTextColor
        }

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (lineNumber < 1) {
            lineNumber = 1
        }
        if (lineNumber > lineCount) {
            lineNumber = lineCount
        }
        val layout = layout
        val start = layout.getLineStart(lineNumber - 1)
        val end = layout.getLineEnd(lineNumber - 1)
        val spannable = SpannableString(text)
        spannable.setSpan(
            ForegroundColorSpan(lineColor),
            start,
            end,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE
        )
        text = spannable
    }


}