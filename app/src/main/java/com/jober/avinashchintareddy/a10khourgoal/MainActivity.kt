package com.jober.avinashchintareddy.a10khourgoal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jober.avinashchintareddy.a10khourgoal.models.HourViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var hourViewModel: HourViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hourViewModel = ViewModelProvider(this).get(HourViewModel::class.java)

        hourViewModel.allSessions.observe(this, Observer { hours ->
            hours?.let { Log.i("MainActivity",hours.get(0).date+"--"+hours.get(0).endTime+"--"+hours.get(0).startTime) }
        })

    }
}
