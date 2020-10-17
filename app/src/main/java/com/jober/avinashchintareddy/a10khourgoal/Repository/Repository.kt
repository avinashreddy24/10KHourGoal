package com.jober.avinashchintareddy.a10khourgoal.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jober.avinashchintareddy.a10khourgoal.models.HoursTable
import com.jober.avinashchintareddy.a10khourgoal.persistant.HourDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val hourDao: HourDao){



    suspend fun insertSession(hours: HoursTable){
        withContext(Dispatchers.IO) {
            // get timestamp and add
            hourDao.insertSession(hours)
        }
    }

    suspend fun updateSession(hours:HoursTable){
        withContext(Dispatchers.IO){
            hours.duration = hours.endTime-hours.startTime
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

    suspend fun getHoursByDate(date: Long): List<HoursTable>{
        return withContext(Dispatchers.IO){
            var session = hourDao.getFromDate(date)

            session
        }
    }

    suspend fun getFromOldest():List<HoursTable>{
        return withContext(Dispatchers.IO){
            var session = hourDao.getAllOldestSessions()
            session
        }
    }

    suspend fun getAllSessionsNoFilters():List<HoursTable>{
        return withContext(Dispatchers.IO){
            var session = hourDao.getAllSessions()
            session
        }
    }


}