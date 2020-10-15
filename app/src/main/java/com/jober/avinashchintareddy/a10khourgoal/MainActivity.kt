package com.jober.avinashchintareddy.a10khourgoal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import com.google.android.material.navigation.NavigationView
import com.jober.avinashchintareddy.a10khourgoal.models.HourViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var hourViewModel: HourViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)



        hourViewModel = ViewModelProvider(this).get(HourViewModel::class.java)


        hourViewModel.allSessions?.observe(this, Observer { hours ->
            hours?.let {

            }
        })

        if (savedInstanceState == null) {
        // get fragment
        val host: NavHostFragment= supportFragmentManager.
            findFragmentById(R.id.host_fragment) as NavHostFragment? ?:return

        // add navcontroller

        val navController = host.navController}

        //assign

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController(R.id.host_fragment))
                ||super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        Log.i("saved instance activity","wefwef")
    }


}
