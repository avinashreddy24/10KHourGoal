package com.jober.avinashchintareddy.a10khourgoal.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.jober.avinashchintareddy.a10khourgoal.Repository.Repository
import com.jober.avinashchintareddy.a10khourgoal.persistant.HourDao
import com.jober.avinashchintareddy.a10khourgoal.persistant.HoursDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HourViewModel(application: Application) :AndroidViewModel(application){

private  val repository: Repository
    val allSessions: LiveData<List<HoursTable>>

    init {
        val hourDao = HoursDatabase.getDatabase(application,viewModelScope).helperDao()
        repository = Repository(hourDao)
        allSessions = repository.allSessions

    }
    fun insert(hour: HoursTable)= viewModelScope.launch (Dispatchers.IO){  }

}