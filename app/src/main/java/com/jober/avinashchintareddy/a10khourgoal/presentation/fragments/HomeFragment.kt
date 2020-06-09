package com.jober.avinashchintareddy.a10khourgoal.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jober.avinashchintareddy.a10khourgoal.R
import com.jober.avinashchintareddy.a10khourgoal.models.HourViewModel
import com.jober.avinashchintareddy.a10khourgoal.views.SystemTimeViewer

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [HomeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private lateinit var hourViewModel: HourViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //  attach
        return inflater.inflate(R.layout.home_host, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        hourViewModel = ViewModelProvider(this).get(HourViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button)?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_history, null)
        }
        view.findViewById<Button>(R.id.btn_end)?.setOnClickListener {
            hourViewModel.stopSession()
            Log.i("onend", "Invoked end")
        }

        view.findViewById<Button>(R.id.btn_start)?.setOnClickListener {
            hourViewModel.startSession()
            Log.i("onstart", "Invoked start")

        }
        view.findViewById<SystemTimeViewer>(R.id.setText_vw).setTimer((1590401410).toString())
        view.findViewById<SystemTimeViewer>(R.id.setText_vw).setShowDate(true)
    }
}



