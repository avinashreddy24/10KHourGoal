package com.jober.avinashchintareddy.a10khourgoal

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch

@Throws(InterruptedException::class)
 fun <T> LiveData<T>.waitForValue(): T {
    val data = arrayOfNulls<Any>(1)
    val latch = CountDownLatch(1)
    val observer = object : Observer<T>{
        override fun onChanged(t: T) {
            data[0] = t
            latch.countDown()
            this@waitForValue.removeObserver(this)
        }
    }

    return data[0] as T
}