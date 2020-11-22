package com.jober.avinashchintareddy.a10khourgoal.presentation.fragments

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.jober.avinashchintareddy.a10khourgoal.R
import com.jober.avinashchintareddy.a10khourgoal.Util
import com.jober.avinashchintareddy.a10khourgoal.models.HourViewModel
import com.jober.avinashchintareddy.a10khourgoal.models.HoursTable
import com.jober.avinashchintareddy.a10khourgoal.models.RecordedListState
import java.text.SimpleDateFormat
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [History.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [History.newInstance] factory method to
 * create an instance of this fragment.
 */
class History : Fragment() {
    private lateinit var hourViewModel: HourViewModel
    lateinit var recyclerView: RecyclerView
    var adapterList: List<HoursTable> = ArrayList<HoursTable>()
    val adapters: HistoryListAdapter? = null
    var listTouchHelper: ItemTouchHelper? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        recyclerView = (view.findViewById<View>(R.id.RecyclerView)) as RecyclerView

        view.findViewById<Button>(R.id.btn_filter)?.setOnClickListener {
            Log.i("onFilter", "Invoked Filter")
/*            var alert: FragmentAlert
            alert = FragmentAlert()
            alert.show(parentFragmentManager, "Alert")*/

            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)



            val dpd = context?.let { it1 ->
                DatePickerDialog(
                    it1,
                    DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                        // Display Selected date in textbox
                        //lblDate.setText("" + dayOfMonth + " " + MONTHS[monthOfYear] + ", " + year)
                        val cal:Calendar = Calendar.getInstance()
                        cal.set(year,monthOfYear,dayOfMonth)

                        hourViewModel.recordedListState.value = RecordedListState.fromDate(cal.timeInMillis)

                    },
                    year,
                    month,
                    day
                )

            }


            dpd?.show()

        }

        view.findViewById<Button>(R.id.older_btn)?.setOnClickListener {
            hourViewModel.recordedListState.value = RecordedListState.olderToNew()
        }
        view.findViewById<Button>(R.id.clear_btn)?.setOnClickListener {
            hourViewModel.recordedListState.value = RecordedListState.noFilter()
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        hourViewModel = ViewModelProvider(requireActivity()).get(HourViewModel::class.java)

        hourViewModel.allSessions.observe(this, Observer { hours ->
            hours?.let {
                Log.i("HistoryFragment", "test" + hours.size);
                adapterList = hours
                if (adapters == null) {
                    val adapters = HistoryListAdapter(adapterList)
                    recyclerView.apply {
                        adapter = adapters
                        layoutManager = LinearLayoutManager(getContext())
                        listTouchHelper = ItemTouchHelper(ListTouchHelperCallback())

                        listTouchHelper?.attachToRecyclerView(this)
//                    addOnScrollListener(object : RecyclerView.OnScrollListener() {
//                        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                            super.onScrollStateChanged(recyclerView, newState)
//                            Log.i("HistoryFragment","test onScroll");
//
//                        }})

                    }
                }


            }

        })
    }


    override fun onStart() {
        super.onStart()
        hourViewModel.recordedListState.observe(this, Observer {
            when (it) {
                is RecordedListState.noFilter -> hourViewModel.getNoFilterHistory()
                is RecordedListState.olderToNew -> hourViewModel.getFromOlderHistory()
                is RecordedListState.fromDate -> hourViewModel.getListFromDate(it.from)


            }
            adapters?.notifyDataSetChanged()
            Log.i("test", "changedState detected")
        })
    }


}


