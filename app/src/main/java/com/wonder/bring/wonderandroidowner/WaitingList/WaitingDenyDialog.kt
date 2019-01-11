package com.wonder.bring.wonderandroidowner.WaitingList

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.wonder.bring.wonderandroidowner.MainActivity
import com.wonder.bring.wonderandroidowner.Network.ApplicationController
import com.wonder.bring.wonderandroidowner.Network.Get.GetChangeServerStatusResponseData
import com.wonder.bring.wonderandroidowner.Network.NetworkService
import com.wonder.bring.wonderandroidowner.R
import kotlinx.android.synthetic.main.dialog_waiting_deny.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WaitingDenyDialog(var ctx: Context, val rvAdapter: WaitingListRecyclerViewAdapter, val position: Int) :
    Dialog(ctx) {

    var btn_type: String = ""

    val OUT_OF_STOCK: String = "재료 소진"
    val ORDER_DELAY: String = "주문 지연"
    val USER_INFO_INCORRECT: String = "고객 정보 부정확"
    val STORE_END: String = "영업 종료"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_waiting_deny)
        window.setBackgroundDrawableResource(R.color.transparent)

        initOnClickListener()

        btn_waiting_deny_dialog_direct.isEnabled = false

    }

    // 보미 서버 통신
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
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
            changeServerStatus(btn_type)
        }

        //주문 지연 버튼 클릭시
        btn_waiting_deny_dialog_delay.setOnClickListener {
            btn_type = ORDER_DELAY

            //버튼누르면 현재 다이얼로그 꺼지고 푸시알림 전송되었다는 다이얼로그 띄우기
            dismiss()
            changeServerStatus(btn_type)
        }

        //고객정보 불활실 버튼 클릭시
        btn_waiting_deny_dialog_incorrect.setOnClickListener {
            btn_type = USER_INFO_INCORRECT

            //버튼누르면 현재 다이얼로그 꺼지고 푸시알림 전송되었다는 다이얼로그 띄우기
            dismiss()
            changeServerStatus(btn_type)
        }

        //영업 종료 버튼 클릭시
        btn_waiting_deny_dialog_storeend.setOnClickListener {
            btn_type = STORE_END

            //버튼누르면 현재 다이얼로그 꺼지고 푸시알림 전송되었다는 다이얼로그 띄우기
            dismiss()
            changeServerStatus(btn_type)
        }

        btn_waiting_deny_dialog_direct.setOnClickListener {
            dismiss()
            changeServerStatus(et_waiting_deny_dialog_direct.text.toString())
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

    fun changeServerStatus(res: String) {

        Log.v(
            "Malibin Debug",
            "내가 요청한 값들 : res = $res storeIdx = " + MainActivity.storeIdx + " orderIdx = " + rvAdapter.dataList[position].orderListIdx
        )

        networkService.getChangeServerStatusRequest(
            "application/json",
            MainActivity.storeIdx,
            rvAdapter.dataList[position].orderListIdx,
            4, //이 다이얼로그는 애초에 거절을 눌럿을때만 실행되니 4로 고정이다.
            res
        ).enqueue(object : Callback<GetChangeServerStatusResponseData> {
            override fun onFailure(call: Call<GetChangeServerStatusResponseData>, t: Throwable) {
                ctx.toast("서버 통신에 실패하였습니다")
            }

            override fun onResponse(
                call: Call<GetChangeServerStatusResponseData>,
                response: Response<GetChangeServerStatusResponseData>
            ) {
                if (response.isSuccessful) {

                    var branch: Int = response.body()!!.status

                    when (branch) {
                        200 -> {
                            Log.v("Malibin Debug", "서버 상태변경 성공")
                            //상태변경 성공인경우
                            customToast()
                            deleteRVItem()
                        }
                    }
                }
            }

        })
    }
}