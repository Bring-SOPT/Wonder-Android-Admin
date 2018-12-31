package com.wonder.bring.wonderandroidowner.WaitingList

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.wonder.bring.wonderandroidowner.R
import kotlinx.android.synthetic.main.dialog_waiting_accept.*

class WaitingAcceptDialog(ctx: Context):AlertDialog(ctx){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_waiting_accept)

        btn_second_dialog_ok.setOnClickListener{

            dismiss()
        }
    }
}