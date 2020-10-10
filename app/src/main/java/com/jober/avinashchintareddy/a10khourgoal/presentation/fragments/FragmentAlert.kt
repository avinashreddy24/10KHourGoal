package com.jober.avinashchintareddy.a10khourgoal.presentation.fragments


import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jober.avinashchintareddy.a10khourgoal.R
import com.jober.avinashchintareddy.a10khourgoal.models.HourViewModel
import com.jober.avinashchintareddy.a10khourgoal.models.RecordedListState

class FragmentAlert : DialogFragment(){
    var counter: Int=0
    private lateinit var hourViewModel: HourViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.alert_layout,container)
    }

   /* override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder :  AlertDialog.Builder
        hourViewModel = ViewModelProvider(requireActivity()).get(HourViewModel::class.java)
        //val cos: HourViewModel by  activityViewModels()
        hourViewModel.recordedListState.observe(this, Observer<RecordedListState>{
            Log.i("test Fragment Alert","changedState detected")
        })

        builder = AlertDialog.Builder(requireContext())
        builder.setTitle("From DialogFragment")
        builder.setNeutralButton("neutral",object :DialogInterface.OnClickListener{
            override fun onClick(dialog:DialogInterface, which:Int) {

                hourViewModel.recordedListState.value=RecordedListState.fromDate(1212)
                            counter++
            }
        })
        builder.setPositiveButton("it's ok ", null)
        builder.setNegativeButton("No", object :DialogInterface.OnClickListener{
            override fun onClick(dialog:DialogInterface, which:Int) {

                dismiss()

            }
        })
        return  builder.create()
    }*/
}