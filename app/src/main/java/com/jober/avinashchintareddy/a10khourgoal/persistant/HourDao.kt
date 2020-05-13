package com.jober.avinashchintareddy.a10khourgoal.persistant

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.jober.avinashchintareddy.a10khourgoal.models.HoursTable

@Dao
interface  HourDao{


    @Update
    fun update(hours: HoursTable)

    @Query("Select * from table_hours where start_time = :date")
    fun getHoursByDate(date: Long): LiveData<List<HoursTable>>

    @Insert(onConflict = REPLACE)
     fun insertSession(hours:HoursTable)

    @Query("Select * from table_hours")
    fun getAllSessions(): LiveData<List<HoursTable>>

    @Query("DELETE FROM table_hours")
    fun clear()

    @Query("Select * from table_hours ORDER BY id DESC LIMIT 1")
    fun getThisSession(): HoursTable?

}