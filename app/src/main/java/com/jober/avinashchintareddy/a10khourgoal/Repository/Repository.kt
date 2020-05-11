package com.jober.avinashchintareddy.a10khourgoal.Repository

import androidx.lifecycle.LiveData
import com.jober.avinashchintareddy.a10khourgoal.models.HoursTable
import com.jober.avinashchintareddy.a10khourgoal.persistant.HourDao
import java.util.*

class Repository(private val hourDao: HourDao){

    val allSessions: LiveData<List<HoursTable>> = hourDao.getAllSessions()

    suspend fun addHours(hours: HoursTable){
        hourDao.insertSession(hours)
    }


}