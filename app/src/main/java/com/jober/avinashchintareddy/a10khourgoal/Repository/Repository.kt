package com.jober.avinashchintareddy.a10khourgoal.Repository

import androidx.lifecycle.LiveData
import com.jober.avinashchintareddy.a10khourgoal.models.HoursTable
import com.jober.avinashchintareddy.a10khourgoal.persistant.HourDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class Repository(private val hourDao: HourDao){

    val allSessions: LiveData<List<HoursTable>> = hourDao.getAllSessions()

    suspend fun insertSession(hours: HoursTable){
        withContext(Dispatchers.IO) {
            // get timestamp and add
            hourDao.insertSession(hours)
        }
    }

    suspend fun updateSession(hours:HoursTable){
        withContext(Dispatchers.IO){
            hourDao.update(hours)
        }
    }

    suspend fun clear(){
        withContext(Dispatchers.IO){
            hourDao.clear()
        }
    }

    suspend fun getThisSessionFromDatabase(): HoursTable?{
        return withContext(Dispatchers.IO){
            var session = hourDao.getThisSession()

            if(session?.startTime !=session?.endTime){
                session = null
            }

            session
        }


    }



}