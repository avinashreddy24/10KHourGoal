package com.jober.avinashchintareddy.a10khourgoal

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch

@Throws(InterruptedException::class)
 fun <T> List<T>.waitForValue(): T {
    val data = arrayOfNulls<Any>(1)
    val latch = CountDownLatch(1)
    val observer = object : Observer<T>{
        override fun onChanged(o: T?) {
            data[0] = o
            latch.countDown()
            this@waitForValue.size
        }
    }

    //this.observeForever(observer)

    return data[0] as T
}