package com.jober.avinashchintareddy.a10khourgoal.presentation.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jober.avinashchintareddy.a10khourgoal.ACTION_START_SERVICE
import com.jober.avinashchintareddy.a10khourgoal.ACTION_STOP_SERVICE
import com.jober.avinashchintareddy.a10khourgoal.R
import com.jober.avinashchintareddy.a10khourgoal.models.HourViewModel
import com.jober.avinashchintareddy.a10khourgoal.models.TimerEvent
import com.jober.avinashchintareddy.a10khourgoal.service.TimerService
import com.jober.avinashchintareddy.a10khourgoal.views.SystemTimeViewer
import kotlinx.coroutines.*
import kotlin.concurrent.thread
import kotlin.math.log
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * HomeFragment for staring a new session
 */
class HomeFragment : Fragment() {

    private lateinit var hourViewModel: HourViewModel
    private var isTimerRunning =false
    lateinit var startButton:Button
    lateinit var stopButton:Button
    lateinit var timerSystem:SystemTimeViewer


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setRetainInstance(true);
        // Inflate the layout for this fragment
        //  attach
        return inflater.inflate(R.layout.home_host, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        hourViewModel = ViewModelProvider(this).get(HourViewModel::class.java)
    }

    private  fun setObservers() {
        TimerService.timerEvent.observe( viewLifecycleOwner, Observer {
            updateUi(it)
        })

        TimerService.timerInMills.observe(viewLifecycleOwner, Observer {
            timerSystem.setTimer(it.toString())
        })
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.i("restored","saved")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        retainInstance=true
        startButton = view.findViewById<Button>(R.id.btn_start)
        stopButton = view.findViewById<Button>(R.id.btn_end)
        timerSystem = view.findViewById<SystemTimeViewer>(R.id.setText_vw)
        timerSystem.setShowDate(false)

        setObservers()
        view.findViewById<Button>(R.id.button)?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_history, null)
        }

        stopButton.setOnClickListener {
            //hourViewModel.stopSession()
            Log.i("onend", "Invoked end")
        }
        startButton.setOnClickListener {
           // hourViewModel.startSession()
            toggleTimer()

        }
    }

    override fun onResume() {
        super.onResume()

    }

    private fun updateUi(event: TimerEvent){
        when(event){
            is TimerEvent.START ->{
                isTimerRunning = true
                startButton.text = "Stop"
            }
            is TimerEvent.END ->{
                isTimerRunning = false
                startButton.text = "Start"
            }
        }
    }



    override fun onPause() {
        super.onPause()

    }

    private fun toggleTimer(){
        if(!isTimerRunning){
            sendCommandToService(ACTION_START_SERVICE)
            Log.i("Fragment"," timmer   started running");
        }else {
            sendCommandToService(ACTION_STOP_SERVICE)
            Log.i("Fragment"," timmer stopped running");
        }
    }

    private fun sendCommandToService(action: String){
        context?.startService(Intent(context,TimerService::class.java).apply{
            this.action = action
        })
    }



    override fun onDestroy() {
        super.onDestroy()
        Log.i("On Destroy","fragment")

    }

    override fun onDetach() {
        super.onDetach()

    }



}



