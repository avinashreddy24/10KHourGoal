package com.jober.avinashchintareddy.a10khourgoal.models

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jober.avinashchintareddy.a10khourgoal.Repository.Repository
import com.jober.avinashchintareddy.a10khourgoal.persistant.HoursDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HourViewModel(application: Application) :AndroidViewModel(application){

private  val repository: Repository
    var allSessions: MutableLiveData<List<HoursTable>> = MutableLiveData()
    private var  currentSession = MutableLiveData<HoursTable?>()
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
            insert(HoursTable())
            currentSession.value=repository.getThisSessionFromDatabase()
        }
    }

    fun stopSession(){
        //insert ending time
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