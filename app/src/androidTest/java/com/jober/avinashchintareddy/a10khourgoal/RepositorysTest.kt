package com.jober.avinashchintareddy.a10khourgoal

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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
import org.junit.Assert.assertEquals
import org.junit.Rule

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

        db = Room.inMemoryDatabaseBuilder(context,HoursDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        hoursDao = db.helperDao()

    }


    @Test
    fun shouldaddsessioninstorage(){
            val start:Long =System.currentTimeMillis()
        var hoursTable= HoursTable(0,start,start,12)
            hoursDao.insertSession(hoursTable)

        val allhours= hoursDao.getAllSessions().waitForValue()
        //assertEquals(allhours[0].duration,12)
    }

    @Test
    fun getsessionfromstartDate(){
        val start:Long =1593775338000
        val startBefore:Long = 1593602538000
        val startAfter:Long = System.currentTimeMillis()
        var hoursTable= HoursTable(1,start,start,12)
        var hoursBefore = HoursTable(2,startBefore,startBefore,13)
        var hoursAfter = HoursTable(3,startAfter,startAfter,15)
        hoursDao.insertSession(hoursTable)
        hoursDao.insertSession(hoursBefore)
        hoursDao.insertSession(hoursAfter)

        var expected :List<HoursTable>  = listOf(hoursTable,hoursAfter)

        val allhours= hoursDao.getFromDate(start)
        assertEquals(allhours.size, 2)
        assertEquals(allhours, listOf(hoursTable,hoursAfter))
    }

    @Test
    fun getDescendingOrder(){
        val start:Long =1593602538000
        val startBefore:Long = 1593775338000
        val startAfter:Long = System.currentTimeMillis()
        var hoursTable= HoursTable(0,start,start,12)
        var hoursBefore = HoursTable(0,startBefore,startBefore,13)
        var hoursAfter = HoursTable(0,startAfter,startAfter,15)

        var expecthoursTable= HoursTable(1,start,start,12)
        var expecthoursBefore = HoursTable(2,startBefore,startBefore,13)
        var expecthoursAfter = HoursTable(3,startAfter,startAfter,15)
        hoursDao.insertSession(hoursTable)
        hoursDao.insertSession(hoursBefore)
        hoursDao.insertSession(hoursAfter)

        var expected :List<HoursTable>  = listOf(hoursTable,hoursAfter)

        val allhours= hoursDao.getAllOldestSessions()

        assertEquals(allhours.size,3)
        assertEquals(allhours, listOf(expecthoursAfter,expecthoursBefore,expecthoursTable))
    }

    @Test
    fun getAscendingOrder(){
        val start:Long =1593775338000
        val startBefore:Long = 1593602538000
        val startAfter:Long = System.currentTimeMillis()
        var hoursTable= HoursTable(1,start,start,12)
        var hoursBefore = HoursTable(2,startBefore,startBefore,13)
        var hoursAfter = HoursTable(3,startAfter,startAfter,15)
        hoursDao.insertSession(hoursTable)
        hoursDao.insertSession(hoursBefore)
        hoursDao.insertSession(hoursAfter)

        var expected :List<HoursTable>  = listOf(hoursTable,hoursAfter)

        val allhours= hoursDao.getAllSessions()

        assertEquals(allhours.size,3)
        assertEquals(allhours, listOf(hoursTable,hoursBefore,hoursAfter))

    }


}


