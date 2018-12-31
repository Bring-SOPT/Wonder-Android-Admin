package com.wonder.bring.wonderandroidowner.OngoingList

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.wonder.bring.wonderandroidowner.R
import kotlinx.android.synthetic.main.dialog_ongoing_ready.*
import kotlinx.android.synthetic.main.dialog_waiting_accept.*

class OngoingReadyDialog(ctx: Context): AlertDialog(ctx){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_ongoing_ready)

        btn_ongoing_ready_dialog_ok.setOnClickListener{

            dismiss()
        }
    }
}