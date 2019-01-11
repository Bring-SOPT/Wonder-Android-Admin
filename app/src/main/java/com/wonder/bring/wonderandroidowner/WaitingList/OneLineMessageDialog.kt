package com.wonder.bring.wonderandroidowner.WaitingList

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.wonder.bring.wonderandroidowner.R
import kotlinx.android.synthetic.main.dialog_oneline_message.*

class OneLineMessageDialog(ctx: Context, val dMessage: String ) : Dialog(ctx){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_oneline_message)

        setMessage(dMessage)



    }

    private fun setMessage(message : String){
        tv_oneline_message_dialog_message.text = message
    }
}