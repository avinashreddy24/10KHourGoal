package com.jober.avinashchintareddy.a10khourgoal.persistant

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.jober.avinashchintareddy.a10khourgoal.models.HoursTable

@Dao
interface  HourDao{

    @Query("Select * from table_hours where date = :date")
    fun getHoursByDate(date: String): LiveData<List<HoursTable>>

    @Insert(onConflict = REPLACE)
     fun insertSession(hours:HoursTable)

    @Query("Select * from table_hours")
    fun getAllSessions(): LiveData<List<HoursTable>>

}