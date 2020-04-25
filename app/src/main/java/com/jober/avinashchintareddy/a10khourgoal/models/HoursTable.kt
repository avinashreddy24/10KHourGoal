package com.jober.avinashchintareddy.a10khourgoal.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_hours")
data class HoursTable (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:Int,
    @ColumnInfo(name="date") val date:String,
    @ColumnInfo(name="start_time") val startTime:String,
    @ColumnInfo(name = "end_time") val endTime:String

)