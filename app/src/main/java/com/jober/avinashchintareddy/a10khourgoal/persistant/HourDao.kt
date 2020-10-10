package com.jober.avinashchintareddy.a10khourgoal.persistant

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import androidx.sqlite.db.SupportSQLiteQuery
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
    fun getAllSessions(): List<HoursTable>

    @Query("DELETE FROM table_hours")
    fun clear()

    @Query("Select * from table_hours ORDER BY id DESC LIMIT 1")
    fun getThisSession(): HoursTable?

    @Query("Select * from table_hours where start_Time >= :date")
    fun getFromDate(date: Long):List<HoursTable>

    @Query("Select * from table_hours ORDER BY start_time  DESC")
    fun getAllOldestSessions(): List<HoursTable>

}