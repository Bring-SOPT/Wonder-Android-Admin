package com.wonder.bring.wonderandroidowner.WaitingList

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.wonder.bring.wonderandroidowner.R
import kotlinx.android.synthetic.main.dialog_waiting_accept.*

class WaitingAcceptDialog(ctx: Context): Dialog(ctx){

    var waitTime : Int = 0

    val oneLineMessageDialog = OneLineMessageDialog(ctx,"주문 접수 알림이 전송되었습니다.")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_waiting_accept)

        initOnClickListener()
    }
    private fun initOnClickListener(){

        //5분 버튼
        btn_waiting_accept_dialog_5m.setOnClickListener {
            waitTime = 5

            //버튼누르면 현재 다이얼로그 꺼지고 푸시알림 전송되었다는 다이얼로그 띄우기
            dismiss()
            oneLineMessageDialog.show()
        }

        //10분 버튼
        btn_waiting_accept_dialog_10m.setOnClickListener {
            waitTime = 10

            //버튼누르면 현재 다이얼로그 꺼지고 푸시알림 전송되었다는 다이얼로그 띄우기
            dismiss()
            oneLineMessageDialog.show()
        }

        //15분 버튼
        btn_waiting_accept_dialog_15m.setOnClickListener {
            waitTime = 15

            //버튼누르면 현재 다이얼로그 꺼지고 푸시알림 전송되었다는 다이얼로그 띄우기
            dismiss()
            oneLineMessageDialog.show()
        }

        //20분 버튼
        btn_waiting_accept_dialog_20m.setOnClickListener {
            waitTime = 20

            //버튼누르면 현재 다이얼로그 꺼지고 푸시알림 전송되었다는 다이얼로그 띄우기
            dismiss()
            oneLineMessageDialog.show()
        }

        //25분 버튼
        btn_waiting_accept_dialog_25m.setOnClickListener {
            waitTime = 25

            //버튼누르면 현재 다이얼로그 꺼지고 푸시알림 전송되었다는 다이얼로그 띄우기
            dismiss()
            oneLineMessageDialog.show()
        }

        //30분 버튼
        btn_waiting_accept_dialog_30m.setOnClickListener {
            waitTime = 30

            //버튼누르면 현재 다이얼로그 꺼지고 푸시알림 전송되었다는 다이얼로그 띄우기
            dismiss()
            oneLineMessageDialog.show()
        }

    }

}