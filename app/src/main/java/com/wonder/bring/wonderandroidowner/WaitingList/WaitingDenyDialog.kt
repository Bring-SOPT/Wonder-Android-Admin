package com.wonder.bring.wonderandroidowner.WaitingList

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.wonder.bring.wonderandroidowner.R
import com.wonder.bring.wonderandroidowner.R.id.btn_waiting_deny_dialog_ok
import kotlinx.android.synthetic.main.dialog_waiting_deny.*

class WaitingDenyDialog(ctx: Context): Dialog(ctx){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_waiting_deny)

        btn_waiting_deny_dialog_ok.setOnClickListener{

            dismiss()
        }
    }
}