package com.jober.avinashchintareddy.a10khourgoal

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.jober.avinashchintareddy.a10khourgoal.Repository.Repository
import com.jober.avinashchintareddy.a10khourgoal.models.HoursTable
import com.jober.avinashchintareddy.a10khourgoal.persistant.HourDao
import com.jober.avinashchintareddy.a10khourgoal.persistant.HoursDatabase
import junit.framework.Assert
import org.junit.Assert.assertEquals
import org.junit.Rule
import kotlin.concurrent.thread

@RunWith(AndroidJUnit4::class)
class RepositorysTest {

    public lateinit var hoursDao: HourDao
    public lateinit var db:HoursDatabase

    private lateinit var repository: Repository
    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    @Before
    fun setup(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        //HoursDatabase.getDatabase(context,viewModelScope).helperDao()
        db = Room.inMemoryDatabaseBuilder(context,HoursDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        hoursDao = db.helperDao()
            //repository = Repository(hoursDao)

    }

    //
    @Test
    fun shouldaddsessioninstorage(){
            val start:Long =System.currentTimeMillis()
        var hoursTable= HoursTable(0,start,start,12)
            hoursDao.insertSession(hoursTable)

        val allhours= hoursDao.getAllSessions().waitForValue()
        assertEquals(allhours[0].duration,12)
    }

//    @Test
//    fun `should be able to retrieve the session`(){
//
//    }
//
//    @Test
//    fun `should get session of a day`(){
//
//    }
}


