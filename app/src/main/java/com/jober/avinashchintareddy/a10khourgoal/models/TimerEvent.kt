package com.jober.avinashchintareddy.a10khourgoal.models

sealed class TimerEvent{
    object START : TimerEvent()
    object END : TimerEvent()
}