package com.jober.avinashchintareddy.a10khourgoal.models

sealed class RecordedListState {
    class noFilter : RecordedListState()
    class olderToNew: RecordedListState()
    class fromDate(val from:Long): RecordedListState()

//    var searchByDate: String?=null ,
//    var searchByDuration: String?=null
}