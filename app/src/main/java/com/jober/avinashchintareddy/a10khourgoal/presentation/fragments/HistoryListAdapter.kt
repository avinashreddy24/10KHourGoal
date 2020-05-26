package com.jober.avinashchintareddy.a10khourgoal.presentation.fragments

import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jober.avinashchintareddy.a10khourgoal.R
import com.jober.avinashchintareddy.a10khourgoal.models.HoursTable
import kotlinx.android.synthetic.main.list_view.view.*

class HistoryListAdapter(private val hoursList:  List<HoursTable>) : RecyclerView.Adapter<HistoryListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryListAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val hoursView = inflater.inflate(R.layout.list_view,parent,false)
        return ViewHolder(hoursView)
    }





    override fun getItemCount(): Int {
        return hoursList.size
    }

    override fun onBindViewHolder(holder: HistoryListAdapter.ViewHolder, position: Int) {
        val currTable: HoursTable = hoursList.get(position)
        val startTime  = holder.startText
        val endTime  = holder.endText
        val hours  = holder.hours

        ///
        startTime.setText(currTable.startTime.toString())
        endTime.setText(currTable.endTime.toString())
        hours.setText(currTable.duration.toString())

        holder.bind()

    }

     class ViewHolder
         (listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val startText = listItemView.findViewById<TextView>(R.id.tx_start);
        val endText=listItemView.findViewById<TextView>(R.id.tx_end);
        val hours=listItemView.findViewById<TextView>(R.id.tx_hours);

        fun bind()=with(itemView){
             setOnLongClickListener{

                 Log.i("ViewHodler","Longclickenabled")
                 true
             }
         }
    }

}