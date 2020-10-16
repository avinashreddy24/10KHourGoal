package com.jober.avinashchintareddy.a10khourgoal.service

import android.content.Intent
import android.util.Log
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jober.avinashchintareddy.a10khourgoal.ACTION_START_SERVICE
import com.jober.avinashchintareddy.a10khourgoal.ACTION_STOP_SERVICE
import com.jober.avinashchintareddy.a10khourgoal.models.TimerEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TimerService : LifecycleService(){

    private  var timeStarted = 0L
    private var isServiceStopped = false
    private var lapTime = 0L
    companion object {
        val timerEvent = MutableLiveData<TimerEvent>()
        val timerInMills = MutableLiveData<Long>()
    }

    override fun onCreate() {
        super.onCreate()
        initValues()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when(it.action){
                ACTION_START_SERVICE ->{
                    Log.i("Service","start service")
                    startForegroundService()
                }
                ACTION_STOP_SERVICE ->{
                    stopService()
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)

    }

    private fun startForegroundService(){
        timerEvent.postValue(TimerEvent.START)
        startTimer()

        timerInMills.observe(this, Observer {
            if(!isServiceStopped){
                Log.i("Service","TikTimer "+ it)
            }
        })
    }

    // initialize values at every start or end
    private fun initValues() {
        timerEvent.postValue(TimerEvent.END)
        timerInMills.postValue(0L)
    }

    // looping through each second for updating timer
    private fun startTimer(){
        timeStarted = System.currentTimeMillis()
        CoroutineScope(Dispatchers.Main).launch {
            while (timerEvent.value!! == TimerEvent.START && !isServiceStopped) {
                lapTime = System.currentTimeMillis() - timeStarted
                timerInMills.postValue(lapTime)
                delay(1000L)
            }
        }

    }

    private fun stopService() {
        isServiceStopped = true
        initValues()
        stopForeground(true)
        stopSelf()
    }}