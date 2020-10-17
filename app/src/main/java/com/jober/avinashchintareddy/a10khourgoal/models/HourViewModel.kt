package com.jober.avinashchintareddy.a10khourgoal.models

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jober.avinashchintareddy.a10khourgoal.Repository.Repository
import com.jober.avinashchintareddy.a10khourgoal.persistant.HoursDatabase
import kotlinx.coroutines.*
import kotlin.math.absoluteValue

class HourViewModel(application: Application) :AndroidViewModel(application){

private  val repository: Repository
    var allSessions: MutableLiveData<List<HoursTable>> = MutableLiveData()
     var  currentSession = MutableLiveData<HoursTable?>()
    val recordedListState:MutableLiveData<RecordedListState> = MutableLiveData()
    val state: MutableLiveData<RecordedListState> = MutableLiveData()

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main+viewModelJob)

    init {
        val hourDao = HoursDatabase.getDatabase(application,viewModelScope).helperDao()
        repository = Repository(hourDao)
        getNoFilterHistory()
        recordedListState.value = RecordedListState.noFilter()
    }
    fun insert(hour: HoursTable) {
        uiScope.launch {
            repository.insertSession(hour)
        }
    }

    fun startSession(){
        uiScope.launch {
            val newSession = HoursTable()
            insert(newSession)
            currentSession.value=repository.getThisSessionFromDatabase()
        }
    }

    fun stopSession(){
        //insert ending time

        uiScope.launch {
            currentSession.value?.endTime=System.currentTimeMillis()

          repository.updateSession(currentSession.value!!)
        }

    }

    fun getNoFilterHistory(){
        uiScope.launch {
            allSessions.value = repository.getAllSessionsNoFilters()
        }
    }
    fun getListFromDate(date: Long){
        uiScope.launch {
            allSessions.value = repository.getHoursByDate(date)
        }
    }

    fun getFromOlderHistory(){
        Log.i("HourViewModel","getFromOlderHistory")

        uiScope.launch {
            allSessions.value =repository.getFromOldest()
        }
    }





    // cancel when before any transaction happens
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}