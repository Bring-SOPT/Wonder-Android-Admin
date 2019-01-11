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
import com.wonder.bring.wonderandroidowner.MainActivity
import com.wonder.bring.wonderandroidowner.Network.Get.OrderListData
import com.wonder.bring.wonderandroidowner.R
import kotlinx.android.synthetic.main.dialog_waiting_accept.*
import org.jetbrains.anko.activityManager

class WaitingAcceptDialog(var ctx: Context, val rvAdapter: WaitingListRecyclerViewAdapter, val position: Int) :
    Dialog(ctx) {

    var waitTime: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_waiting_accept)
        window.setBackgroundDrawableResource(R.color.transparent)

        btn_waiting_accept_dialog_ok.isEnabled = false

        initOnClickListener()
    }

    private fun initOnClickListener() {

        et_dialog_waiting_writeminute.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    if (et_dialog_waiting_writeminute.text.toString().equals("")) {
                        btn_waiting_accept_dialog_ok.isEnabled = false
                    } else {
                        btn_waiting_accept_dialog_ok.isEnabled = true
                    }
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

            })

        //5분 버튼
        btn_waiting_accept_dialog_5m.setOnClickListener {
            waitTime = 5

            //버튼누르면 현재 다이얼로그 꺼지고 푸시알림 전송되었다는 다이얼로그 띄우기
            dismiss()
            customToast()
            deleteRVItem()
        }

        //10분 버튼
        btn_waiting_accept_dialog_10m.setOnClickListener {
            waitTime = 10

            //버튼누르면 현재 다이얼로그 꺼지고 푸시알림 전송되었다는 다이얼로그 띄우기
            dismiss()
            customToast()
            deleteRVItem()
        }

        //15분 버튼
        btn_waiting_accept_dialog_15m.setOnClickListener {
            waitTime = 15

            //버튼누르면 현재 다이얼로그 꺼지고 푸시알림 전송되었다는 다이얼로그 띄우기
            dismiss()
            customToast()
            deleteRVItem()
        }

        //20분 버튼
        btn_waiting_accept_dialog_20m.setOnClickListener {
            waitTime = 20

            //버튼누르면 현재 다이얼로그 꺼지고 푸시알림 전송되었다는 다이얼로그 띄우기
            dismiss()
            customToast()
            deleteRVItem()
        }

        //25분 버튼
        btn_waiting_accept_dialog_25m.setOnClickListener {
            waitTime = 25

            //버튼누르면 현재 다이얼로그 꺼지고 푸시알림 전송되었다는 다이얼로그 띄우기
            dismiss()
            customToast()
            deleteRVItem()
        }

        //30분 버튼
        btn_waiting_accept_dialog_30m.setOnClickListener {
            waitTime = 30

            //버튼누르면 현재 다이얼로그 꺼지고 푸시알림 전송되었다는 다이얼로그 띄우기
            dismiss()
            customToast()
            deleteRVItem()
        }

        btn_waiting_accept_dialog_ok.setOnClickListener {

            dismiss()
            customToast()
            deleteRVItem()
        }


    }

    private fun customToast() {

        var toast = Toast.makeText(ctx, "주문 접수 알림이 전송되었습니다.", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)

        var group: ViewGroup = toast.view as ViewGroup

        var tv: TextView = group.getChildAt(0) as TextView
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)

        toast.show()

    }

    //파라미터로 전달받은 WaitingListRecyclerViewAdapter를 통해 아이템을 제거하는 함수
    fun deleteRVItem() {
        addOngoingRVItem(rvAdapter.dataList[position])
        rvAdapter.deleteItem(position)
    }

    //메인액티비티에 있는 callOnGoingAddRVItem 메소드를 호출
    //그럼 액티비티에서는 OnGoingFragment에 있는 addRVItem 함수를 호출함
    fun addOngoingRVItem(item: OrderListData) {
        (ctx as MainActivity).callOnGoingAddRVItem(item)
    }

}