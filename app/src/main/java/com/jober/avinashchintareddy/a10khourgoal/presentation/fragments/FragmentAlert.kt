package com.jober.avinashchintareddy.a10khourgoal.presentation.fragments


import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class FragmentAlert : DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder :  AlertDialog.Builder
        builder = AlertDialog.Builder(requireContext())
        builder.setTitle("From DialogFragment")
        builder.setPositiveButton("it's ok ", null)
        builder.setNegativeButton("No", object :DialogInterface.OnClickListener{
            override fun onClick(dialog:DialogInterface, which:Int) {
                dismiss()
            }
        })
        return  builder.create()
    }
}