package com.jober.avinashchintareddy.a10khourgoal.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
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

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main+viewModelJob)

    init {
        val hourDao = HoursDatabase.getDatabase(application,viewModelScope).helperDao()
        repository = Repository(hourDao)
        allSessions = repository.allSessions

    }
    fun insert(hour: HoursTable) {
        uiScope.launch {
            repository.addHours(hour)
        }
    }


    // cancel when before any transaction happens
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}