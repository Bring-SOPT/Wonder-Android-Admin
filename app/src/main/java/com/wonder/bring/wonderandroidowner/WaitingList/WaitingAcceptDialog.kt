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
import com.wonder.bring.wonderandroidowner.Network.Get.OrderListData
import com.wonder.bring.wonderandroidowner.Network.NetworkService
import com.wonder.bring.wonderandroidowner.R
import kotlinx.android.synthetic.main.dialog_waiting_accept.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WaitingAcceptDialog(var ctx: Context, val rvAdapter: WaitingListRecyclerViewAdapter, val position: Int) :
    Dialog(ctx) {

    var waitTime: Int = 0

    // 보미 서버 통신
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }


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
            changeServerStatus("$waitTime 분")
        }

        //10분 버튼
        btn_waiting_accept_dialog_10m.setOnClickListener {
            waitTime = 10

            //버튼누르면 현재 다이얼로그 꺼지고 푸시알림 전송되었다는 다이얼로그 띄우기
            dismiss()
            changeServerStatus("$waitTime 분")
        }

        //15분 버튼
        btn_waiting_accept_dialog_15m.setOnClickListener {
            waitTime = 15

            //버튼누르면 현재 다이얼로그 꺼지고 푸시알림 전송되었다는 다이얼로그 띄우기
            dismiss()
            changeServerStatus("$waitTime 분")
        }

        //20분 버튼
        btn_waiting_accept_dialog_20m.setOnClickListener {
            waitTime = 20

            //버튼누르면 현재 다이얼로그 꺼지고 푸시알림 전송되었다는 다이얼로그 띄우기
            dismiss()
            changeServerStatus("$waitTime 분")
        }

        //25분 버튼
        btn_waiting_accept_dialog_25m.setOnClickListener {
            waitTime = 25

            //버튼누르면 현재 다이얼로그 꺼지고 푸시알림 전송되었다는 다이얼로그 띄우기
            dismiss()
            changeServerStatus("$waitTime 분")
        }

        //30분 버튼
        btn_waiting_accept_dialog_30m.setOnClickListener {
            waitTime = 30

            //버튼누르면 현재 다이얼로그 꺼지고 푸시알림 전송되었다는 다이얼로그 띄우기
            dismiss()
            changeServerStatus("$waitTime 분")
        }

        btn_waiting_accept_dialog_ok.setOnClickListener {

            dismiss()
            changeServerStatus(et_dialog_waiting_writeminute.text.toString())

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
        rvAdapter.dataList[position].state = 1
        addOngoingRVItem(rvAdapter.dataList[position])
        rvAdapter.deleteItem(position)
    }

    //메인액티비티에 있는 callOnGoingAddRVItem 메소드를 호출
    //그럼 액티비티에서는 OnGoingFragment에 있는 addRVItem 함수를 호출함
    fun addOngoingRVItem(item: OrderListData) {
        (ctx as MainActivity).callOnGoingAddRVItem(item)
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
            1, //이 다이얼로그는 애초에 접수를 눌럿을때만 실행되니 1로 고정이다.
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