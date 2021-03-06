package com.jober.avinashchintareddy.a10khourgoal.models

import android.provider.Settings
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_hours")
data class HoursTable (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:Long =0L,
    @ColumnInfo(name="start_time") var startTime:Long =System.currentTimeMillis(),
    @ColumnInfo(name = "end_time") var endTime:Long =startTime,
    @ColumnInfo(name = "duration_time") var duration:Long =endTime-startTime



)