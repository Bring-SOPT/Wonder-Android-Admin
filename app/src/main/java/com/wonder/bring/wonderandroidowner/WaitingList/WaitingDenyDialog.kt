package com.wonder.bring.wonderandroidowner.WaitingList

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.wonder.bring.wonderandroidowner.R
import com.wonder.bring.wonderandroidowner.R.id.btn_waiting_deny_dialog_ok
import kotlinx.android.synthetic.main.dialog_waiting_deny.*

class WaitingDenyDialog(ctx: Context): Dialog(ctx){

    var btn_type : Int = -1

    val OUT_OF_STOCK : Int = 0
    val ORDER_DELAY : Int = 1
    val USER_INFO_INCORRECT : Int = 2
    val STORE_END : Int = 3

    val oneLineMessageDialog = OneLineMessageDialog(ctx,"주문 거절 알림이 전송되었습니다.")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_waiting_deny)
        window.setBackgroundDrawableResource(R.color.transparent)

        initOnClickListener()

    }

    private fun initOnClickListener(){

        //재료 소진 버튼 클릭시
        btn_waiting_deny_dialog_outofstock.setOnClickListener {
            btn_type = OUT_OF_STOCK

            //버튼누르면 현재 다이얼로그 꺼지고 푸시알림 전송되었다는 다이얼로그 띄우기
            dismiss()
            oneLineMessageDialog.show()
        }

        //주문 지연 버튼 클릭시
        btn_waiting_deny_dialog_delay.setOnClickListener {
            btn_type = ORDER_DELAY

            //버튼누르면 현재 다이얼로그 꺼지고 푸시알림 전송되었다는 다이얼로그 띄우기
            dismiss()
            oneLineMessageDialog.show()
        }

        //고객정보 불활실 버튼 클릭시
        btn_waiting_deny_dialog_incorrect.setOnClickListener {
            btn_type = USER_INFO_INCORRECT

            //버튼누르면 현재 다이얼로그 꺼지고 푸시알림 전송되었다는 다이얼로그 띄우기
            dismiss()
            oneLineMessageDialog.show()
        }

        //영업 종료 버튼 클릭시
        btn_waiting_deny_dialog_storeend.setOnClickListener {
            btn_type = STORE_END

            //버튼누르면 현재 다이얼로그 꺼지고 푸시알림 전송되었다는 다이얼로그 띄우기
            dismiss()
            oneLineMessageDialog.show()
        }

        //취소 버튼 클릭시
        btn_waiting_deny_dialog_cancle.setOnClickListener {
            dismiss()
        }

        //확인 버튼 클릭시
        //직접 입력에 값을 넣지않으면 동작하지 않게 막아놔야한다.
        btn_waiting_deny_dialog_ok.setOnClickListener{


            //버튼누르면 현재 다이얼로그 꺼지고 푸시알림 전송되었다는 다이얼로그 띄우기
            dismiss()
            oneLineMessageDialog.show()
        }
    }
}