package com.jober.avinashchintareddy.a10khourgoal.models

import android.app.Application
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
    val allSessions: LiveData<List<HoursTable>>
    private var  currentSession = MutableLiveData<HoursTable?>()
    val recordedListState:MutableLiveData<RecordedListState> = MutableLiveData()

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main+viewModelJob)

    init {
        val hourDao = HoursDatabase.getDatabase(application,viewModelScope).helperDao()
        repository = Repository(hourDao)
        allSessions = repository.allSessions
        recordedListState.value = RecordedListState()

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




    // cancel when before any transaction happens
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}