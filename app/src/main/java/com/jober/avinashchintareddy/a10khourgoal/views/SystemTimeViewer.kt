package com.jober.avinashchintareddy.a10khourgoal.views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.getSystemService
import com.jober.avinashchintareddy.a10khourgoal.R
import com.jober.avinashchintareddy.a10khourgoal.Util
import org.w3c.dom.Text
import java.util.concurrent.TimeUnit

class SystemTimeViewer(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs){
    var mshowDate:Boolean
    var offSet: Boolean = false
    var msetTimer:String?
    val dateText: TextView
    val hourText: TextView
    val minutText: TextView
    val secndsText: TextView
      var  mutilFormat: Util?=null


    init {
    inflate(context,R.layout.time_viewer,this)
    dateText = findViewById(R.id.tx_date)
    hourText = findViewById(R.id.tx_hr)
    minutText = findViewById(R.id.tx_min)
    secndsText = findViewById(R.id.tx_sec)
    context.theme.obtainStyledAttributes(attrs, R.styleable.SystemTimeViewer,0,0).apply {
        try{
            mshowDate = getBoolean(R.styleable.SystemTimeViewer_showDate,false)
            msetTimer = getString(R.styleable.SystemTimeViewer_setTimer)
            dateText.visibility= dateVisibility()
            if(msetTimer!=null){
                setUtil((msetTimer)!!.toLong())
            }
            updateFormat()
        }
        finally {
            recycle()
        }


    }


}

    private fun updateFormat(){
        Log.i("System Timer","UpdateFormater")
        when(offSet){
           true  ->{
                Log.i("System Timer","offset IN")

                hourText.text=TimeUnit.HOURS.convert(msetTimer!!.toLong(), TimeUnit.MILLISECONDS).toString()
                    minutText.text=TimeUnit.MINUTES.convert(msetTimer!!.toLong(), TimeUnit.MILLISECONDS).toString()
                    secndsText.text=TimeUnit.SECONDS.convert(msetTimer!!.toLong(), TimeUnit.MILLISECONDS).toString()
            }

             false -> {
                 Log.i("System Timer","offset Off")

                 if(mutilFormat!=null  ){

                    dateText.text = mutilFormat?.mDate
                    hourText.text=mutilFormat?.mHour
                    minutText.text=mutilFormat?.mMinute
                    secndsText.text=mutilFormat?.mSecond
                }
            }
        }
    }


    fun setUtil(value:Long){
        mutilFormat = Util(value)
    }

   private fun dateVisibility():Int{
       when(mshowDate){
           true-> return View.VISIBLE
           false-> return  View.GONE
       }
    }
    fun showDate(): Boolean{
        return mshowDate
    }

    fun setShowDate(showDate: Boolean,offsetValue: Boolean){
        mshowDate = showDate
        offSet= offsetValue
        dateText.visibility= dateVisibility()
        invalidate()
        requestLayout()
    }

    fun setModeOffset(offsetValue: Boolean){
        invalidate()
        requestLayout()

    }
    fun setString(data: String){
        msetTimer = data
        invalidate()
        requestLayout()
    }
    fun getString(): String?{
        return msetTimer
    }

    fun setTimer(timeValue:String){
        msetTimer=timeValue


        setUtil(timeValue.toLong())

        updateFormat()


        invalidate()
    }



}