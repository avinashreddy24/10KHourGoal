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
import junit.framework.Assert.assertEquals
import org.junit.Rule

class RepositoryTest {

    @Test
    fun convertmillToTimeStamp(){
        // get repository utils System.mills to Date And Time
    }

    @Test
    fun compareMillsToDate(){
        //Date in utc to System mills if greater
    }

}


