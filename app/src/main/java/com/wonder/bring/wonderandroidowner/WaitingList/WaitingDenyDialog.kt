package com.wonder.bring.wonderandroidowner.WaitingList

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.wonder.bring.wonderandroidowner.R
import kotlinx.android.synthetic.main.dialog_waiting_deny.*

class WaitingDenyDialog(var ctx: Context, val rvAdapter: WaitingListRecyclerViewAdapter, val position: Int) :
    Dialog(ctx) {

    var btn_type: Int = -1

    val OUT_OF_STOCK: Int = 0
    val ORDER_DELAY: Int = 1
    val USER_INFO_INCORRECT: Int = 2
    val STORE_END: Int = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_waiting_deny)
        window.setBackgroundDrawableResource(R.color.transparent)

        initOnClickListener()

        btn_waiting_deny_dialog_direct.isEnabled = false

    }

    private fun initOnClickListener() {

        et_waiting_deny_dialog_direct.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    if (et_waiting_deny_dialog_direct.text.toString().equals("")) {
                        btn_waiting_deny_dialog_direct.isEnabled = false
                    } else {
                        btn_waiting_deny_dialog_direct.isEnabled = true
                    }
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

            })

        //재료 소진 버튼 클릭시
        btn_waiting_deny_dialog_outofstock.setOnClickListener {
            btn_type = OUT_OF_STOCK

            //버튼누르면 현재 다이얼로그 꺼지고 푸시알림 전송되었다는 다이얼로그 띄우기
            dismiss()
            customToast()
            deleteRVItem()
        }

        //주문 지연 버튼 클릭시
        btn_waiting_deny_dialog_delay.setOnClickListener {
            btn_type = ORDER_DELAY

            //버튼누르면 현재 다이얼로그 꺼지고 푸시알림 전송되었다는 다이얼로그 띄우기
            dismiss()
            customToast()
            deleteRVItem()
        }

        //고객정보 불활실 버튼 클릭시
        btn_waiting_deny_dialog_incorrect.setOnClickListener {
            btn_type = USER_INFO_INCORRECT

            //버튼누르면 현재 다이얼로그 꺼지고 푸시알림 전송되었다는 다이얼로그 띄우기
            dismiss()
            customToast()
            deleteRVItem()
        }

        //영업 종료 버튼 클릭시
        btn_waiting_deny_dialog_storeend.setOnClickListener {
            btn_type = STORE_END

            //버튼누르면 현재 다이얼로그 꺼지고 푸시알림 전송되었다는 다이얼로그 띄우기
            dismiss()
            customToast()
            deleteRVItem()
        }

        btn_waiting_deny_dialog_direct.setOnClickListener {
            dismiss()
            customToast()
            deleteRVItem()
        }


    }

    private fun customToast() {

        var toast = Toast.makeText(ctx, "주문 거절 알림이 전송되었습니다.", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)

        var group: ViewGroup = toast.view as ViewGroup

        var tv: TextView = group.getChildAt(0) as TextView
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)

        toast.show()

    }

    fun deleteRVItem() {
        rvAdapter.deleteItem(position)
    }
}