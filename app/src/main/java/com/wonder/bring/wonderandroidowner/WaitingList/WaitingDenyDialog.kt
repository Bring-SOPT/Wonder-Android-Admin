package com.wonder.bring.wonderandroidowner.WaitingList

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.wonder.bring.wonderandroidowner.R
import kotlinx.android.synthetic.main.dialog_waiting_deny.*

class WaitingDenyDialog(ctx: Context):AlertDialog(ctx){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_waiting_deny)

        btn_waiting_deny_dialog_ok.setOnClickListener{

            dismiss()
        }
    }
}