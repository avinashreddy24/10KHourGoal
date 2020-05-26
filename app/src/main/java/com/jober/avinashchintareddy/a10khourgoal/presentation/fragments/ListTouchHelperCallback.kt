package com.jober.avinashchintareddy.a10khourgoal.presentation.fragments

import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ListTouchHelperCallback: ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return makeMovementFlags(
            ItemTouchHelper.ACTION_STATE_IDLE,
            ItemTouchHelper.START or ItemTouchHelper.END
        )

    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {

        return true
    }

    override fun isLongPressDragEnabled(): Boolean {
        //return super.isLongPressDragEnabled()
        return true
    }


    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

        Log.i("Recycler view","direction"+direction)

    }

}