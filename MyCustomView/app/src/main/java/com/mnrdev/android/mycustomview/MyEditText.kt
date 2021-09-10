package com.mnrdev.android.mycustomview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.res.ResourcesCompat

class MyEditText : AppCompatEditText, View.OnTouchListener {

    internal lateinit var mClearButtonImage : Drawable

    constructor(context : Context): super(context){

    }
    constructor(context: Context, attrs : AttributeSet): super(context,attrs){

    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr : Int): super(context, attrs, defStyleAttr){


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    private fun init(){
        mClearButtonImage = ResourcesCompat.getDrawable(resources,R.drawable.ic_baseline_close_24,null) as Drawable
        setOnTouchListener(this)
    }

    private fun showClearButton(){
        setCompoundDrawablesWithIntrinsicBounds(null,null,mClearButtonImage,null)
    }

    private fun hideClearButton(){
        setCompoundDrawablesWithIntrinsicBounds(null,null,null,null)
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        if (compoundDrawables[2] != null){
            val clearButtonStart : Float
            val clearButtonEnd : Float
            var isClearButtonClicked = false
            when (layoutDirection){
                View.LAYOUT_DIRECTION_RTL -> {
                    clearButtonEnd = (mClearButtonImage.intrinsicWidth + paddingStart).toFloat()
                    when {
                        event.x < clearButtonEnd -> isClearButtonClicked = true
                    }
                }
                else -> {
                    clearButtonStart = (width - paddingEnd - mClearButtonImage.intrinsicWidth).toFloat()
                    when {
                        event.x > clearButtonStart -> isClearButtonClicked = true
                    }
                }
            }
            when {
                isClearButtonClicked -> when {
                    event.action == MotionEvent.ACTION_DOWN -> {
                        mClearButtonImage = ResourcesCompat.getDrawable(resources,R.drawable.ic_baseline_close_24,null) as Drawable
                        showClearButton()
                        return true
                    }
                    event.action == MotionEvent.ACTION_UP -> {
                        mClearButtonImage = ResourcesCompat.getDrawable(resources,R.drawable.ic_baseline_close_24,null) as Drawable
                        when {
                            text != null -> text?.clear()
                        }
                        hideClearButton()
                        return true
                    }
                    else -> return false
                }
                else -> return false
            }
        }
        return false
    }
}