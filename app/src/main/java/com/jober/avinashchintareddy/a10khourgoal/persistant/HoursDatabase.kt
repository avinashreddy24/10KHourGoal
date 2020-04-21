package com.jober.avinashchintareddy.a10khourgoal.persistant

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

@Database(entities = arrayOf(HoursDatabase::class),version = 1,exportSchema = false)
public abstract class HoursDatabase :RoomDatabase(){

    abstract fun Dao():Dao

    private class DaoCallback(
        private val scope: CoroutineScope
    ):RoomDatabase.Callback(){

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {  }
            }
        }
    }

    companion object {

        @Volatile
        private var INSTANCE: HoursDatabase?=null

        fun getDatabase(context: Context):HoursDatabase{
            val tempInstance = INSTANCE
            if(tempInstance !=null){
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HoursDatabase::class.java,
                    "hours_database"
                ).build()
                INSTANCE = instance
                return instance

            }
        }
    }
}