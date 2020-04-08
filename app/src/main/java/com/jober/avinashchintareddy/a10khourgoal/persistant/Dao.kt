package com.jober.avinashchintareddy.a10khourgoal.persistant

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.jober.avinashchintareddy.a10khourgoal.models.HoursTable

@Dao
interface  HoursTableDao{

    @Query("Select * from table_hours where date = :date")
    fun getHoursByDate(date: String): LiveData<List<HoursTable>>

    @Insert(onConflict = REPLACE)
    suspend fun insertSession(date: String,startDate:String,endDate:String)
}