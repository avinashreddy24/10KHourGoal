package com.jober.avinashchintareddy.a10khourgoal.persistant

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jober.avinashchintareddy.a10khourgoal.models.HoursTable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [HoursTable::class],version = 1,exportSchema = true)
public abstract class HoursDatabase :RoomDatabase(){

    abstract fun helperDao():HourDao

    private class DaoCallback(
        private val scope: CoroutineScope
    ):RoomDatabase.Callback(){

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    var helperDao= database.helperDao();

                    val start:Long =System.currentTimeMillis()
                    var hoursTable= HoursTable(0,start,start,12)
                  helperDao.insertSession(hoursTable)
//                    var data1 = HoursTable(0,"27 Aug 2019", "4:00 AM","5:00 PM")
//                    helperDao.insertSession(data1)
//                    var data2 = HoursTable(0,"29 Aug 2019", "4:00 AM","5:00 PM")
//                    helperDao.insertSession(data2)

                }
            }
        }
    }

    companion object {

        @Volatile
        private var INSTANCE: HoursDatabase?=null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ):HoursDatabase{

            return INSTANCE ?: synchronized(this){
                val instance =Room.databaseBuilder(
                    context.applicationContext,
                    HoursDatabase::class.java,
                    "hours_database"
                )
                    .addCallback(DaoCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }


            }
        }

}