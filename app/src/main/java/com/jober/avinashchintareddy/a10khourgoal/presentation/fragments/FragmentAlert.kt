//package com.jober.avinashchintareddy.a10khourgoal.presentation.fragments
//
//
//import android.app.DatePickerDialog
//import android.app.Dialog
//import android.content.Context
//import android.content.DialogInterface
//import android.os.Bundle
//import android.text.method.DateKeyListener
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.DatePicker
//import androidx.appcompat.app.AlertDialog
//import androidx.fragment.app.DialogFragment
//import androidx.fragment.app.activityViewModels
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProvider
//import com.jober.avinashchintareddy.a10khourgoal.R
//import com.jober.avinashchintareddy.a10khourgoal.models.HourViewModel
//import com.jober.avinashchintareddy.a10khourgoal.models.RecordedListState
//
//class FragmentAlert : DatePickerDialog(var context: Context,var  dateListener: DatePickerDialog.OnDateSetListener ,
//   var year: Long){
//    private lateinit var hourViewModel: HourViewModel
//
//    lateinit var  datePick:DatePicker
//
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
////        val view:View= inflater.inflate(R.layout.alert_layout,container)
////       datePick =  view.findViewById<DatePicker>(R.id.datePicker1)
//
//        return DatePickerDialog(activity,this,2020,08 )
//    }
//
//
//
//    override fun onResume() {
//        super.onResume()
//        //datePick.setOnDateChangedListener(DatePicker.OnDateChangedListener(){
//
//        //})
//    }
//
//
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
////        var builder :  AlertDialog.Builder
////        hourViewModel = ViewModelProvider(requireActivity()).get(HourViewModel::class.java)
////        //val cos: HourViewModel by  activityViewModels()
////        hourViewModel.recordedListState.observe(this, Observer<RecordedListState>{
////            Log.i("test Fragment Alert","changedState detected")
////        })
////
////        builder = AlertDialog.Builder(requireContext())
////        builder.setTitle("From DialogFragment")
////        builder.setNeutralButton("neutral",object :DialogInterface.OnClickListener{
////            override fun onClick(dialog:DialogInterface, which:Int) {
////
////                hourViewModel.recordedListState.value=RecordedListState.fromDate(1212)
////            }
////        })
////        builder.setPositiveButton("it's ok ", null)
////        builder.setNegativeButton("No", object :DialogInterface.OnClickListener{
////            override fun onClick(dialog:DialogInterface, which:Int) {
////
////                dismiss()
////
////            }
////        })
//        return  DatePickerDialog(context,DatePickerDialog.OnDateSetListener( ),2020,08, 08)
//
//    }
//
//
//
//    override fun onDateChanged(view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
//
//    }
//}
//
//
