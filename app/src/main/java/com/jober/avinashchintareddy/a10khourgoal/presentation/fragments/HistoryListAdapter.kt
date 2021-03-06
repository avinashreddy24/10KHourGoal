package com.jober.avinashchintareddy.a10khourgoal.presentation.fragments

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.jober.avinashchintareddy.a10khourgoal.R
import com.jober.avinashchintareddy.a10khourgoal.models.HoursTable
import com.jober.avinashchintareddy.a10khourgoal.views.SystemTimeViewer
import kotlinx.android.synthetic.main.list_view.view.*

class HistoryListAdapter(private val hoursList:  List<HoursTable>) : RecyclerView.Adapter<HistoryListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val hoursView = inflater.inflate(R.layout.list_view,parent,false)
        return ViewHolder(hoursView)
    }





    override fun getItemCount(): Int {
        return hoursList.size
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val currTable: HoursTable = hoursList.get(position)
        val startTime  = holder.startText
        val endTime  = holder.endText
        val hours  = holder.hours

        startTime.setShowDate(true,false)
        endTime.setShowDate(true,false)
        hours.setShowDate(false,true)

        startTime.setTimer(currTable.startTime.toString())
        endTime.setTimer(currTable.endTime.toString())
        hours.setTimer(currTable.duration.toString())
        if(currTable.startTime == currTable.endTime ){
            Log.i("ViewHodler","position "+position)

            holder.card.setCardBackgroundColor(Color.YELLOW)


        }
        else{
            holder.card.setCardBackgroundColor(Color.WHITE)
        }

        holder.bind()

    }

     class ViewHolder
         (listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val startText = listItemView.findViewById<SystemTimeViewer>(R.id.tx_start);
        val endText=listItemView.findViewById<SystemTimeViewer>(R.id.tx_end)
        val hours=listItemView.findViewById<SystemTimeViewer>(R.id.tx_hours)
         val card= listItemView.findViewById<CardView>(R.id.cd_view)




         fun bind()=with(itemView){

             setOnLongClickListener{

                 Log.i("ViewHodler","Longclickenabled")
                 true
             }

         }



    }

}