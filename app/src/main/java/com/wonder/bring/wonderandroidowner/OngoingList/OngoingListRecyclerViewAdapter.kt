package com.wonder.bring.wonderandroidowner.OngoingList

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.wonder.bring.wonderandroidowner.MainActivity
import com.wonder.bring.wonderandroidowner.Network.ApplicationController
import com.wonder.bring.wonderandroidowner.Network.Get.GetChangeServerStatusResponseData
import com.wonder.bring.wonderandroidowner.Network.Get.OrderListData
import com.wonder.bring.wonderandroidowner.Network.NetworkService
import com.wonder.bring.wonderandroidowner.R
import com.wonder.bring.wonderandroidowner.R.drawable.waiting_order_border
import com.wonder.bring.wonderandroidowner.SizeConvertor
import com.wonder.bring.wonderandroidowner.WaitingList.OneLineMessageDialog
import org.jetbrains.anko.backgroundColorResource
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class OngoingListRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<OrderListData>) :
    RecyclerView.Adapter<OngoingListRecyclerViewAdapter.Holder>() {

    // 보미 서버 통신
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_ongoing, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        var date: Date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataList[position].time)

        var orderDate = SimpleDateFormat("yyyy.MM.dd").format(date)
        var orderTime = SimpleDateFormat("MM:ss").format(date)

        holder.tv_date.text = orderDate
        holder.tv_time.text = orderTime
        holder.tv_orderNum.text = dataList[position].orderListIdx.toString()

        holder.tv_nickname.text = dataList[position].nick
        holder.tv_menu.text = dataList[position].firstMenu.menuName
        holder.tv_sizeAmount.text =
                (SizeConvertor.parseSizeString(dataList[position].firstMenu.size) + " / " + dataList[position].firstMenu.orderCount + "개")

        holder.tv_cost.text = (dataList[position].firstMenu.menuCountPrice.toString() + "원")
        holder.tv_request.text = dataList[position].firstMenu.memo

        //주문접수 상태인경우
        if (dataList[position].state == 1) {
            holder.btn_ready.backgroundResource = R.drawable.waiting_order_border
            holder.btn_ready.isEnabled = true

            holder.btn_gave.backgroundResource = R.drawable.delivery_complete
            holder.btn_gave.isEnabled = false
        }
        //제조완료 상태인경우
        if (dataList[position].state == 2) {
            holder.btn_ready.isEnabled = false
            holder.btn_ready.backgroundResource = R.drawable.delivery_complete

            holder.btn_gave.isEnabled = true
            holder.btn_gave.backgroundResource = R.drawable.waiting_order_border
        }

        holder.btn_ready.setOnClickListener {

            //서버통신 그냥 때려박음 ㅋㅋ시발------------------------------------------------------------------------
            Log.v(
                "Malibin Debug",
                "내가 요청한 값들 :  storeIdx = " + MainActivity.storeIdx + " orderIdx = " + dataList[position].orderListIdx
            )

            networkService.getChangeServerStatus2Request(
                "application/json",
                MainActivity.storeIdx,
                dataList[position].orderListIdx,
                2 //이버튼 누를때는 항상 2로 바꾸는걸 요청
            ).enqueue(object : Callback<GetChangeServerStatusResponseData> {
                override fun onFailure(call: Call<GetChangeServerStatusResponseData>, t: Throwable) {
                    ctx.toast("서버 통신에 실패하였습니다")
                    Log.e("Malibin Debug",t.toString())
                }

                override fun onResponse(
                    call: Call<GetChangeServerStatusResponseData>,
                    response: Response<GetChangeServerStatusResponseData>
                ) {
                    Log.v("Malibin Debug","응답은 왔는데 왜 안되냐? : "+response.body().toString())
                    if (response.isSuccessful) {

                        var branch: Int = response.body()!!.status

                        when (branch) {
                            200 -> {
                                Log.v("Malibin Debug", "서버 상태변경 성공")
                                //상태변경 성공인경우
                                customToast()

                                holder.btn_ready.isEnabled = false
                                holder.btn_ready.backgroundResource = R.drawable.delivery_complete

                                holder.btn_gave.isEnabled = true
                                holder.btn_gave.backgroundResource = R.drawable.waiting_order_border

                                dataList[position].state = 2
                            }
                        }
                    }
                }

            })
            //서버통신 그냥 때려박음 ㅋㅋ시발------------------------------------------------------------------------

        }

        holder.btn_gave.setOnClickListener {

            //서버통신 그냥 때려박음 ㅋㅋ시발------------------------------------------------------------------------
            Log.v(
                "Malibin Debug",
                "내가 요청한 값들 :  storeIdx = " + MainActivity.storeIdx + " orderIdx = " + dataList[position].orderListIdx
            )

            networkService.getChangeServerStatus2Request(
                "application/json",
                MainActivity.storeIdx,
                dataList[position].orderListIdx,
                3 //이 버튼 누를때는 항상 3으로 바꾸는 걸 요청
            ).enqueue(object : Callback<GetChangeServerStatusResponseData> {
                override fun onFailure(call: Call<GetChangeServerStatusResponseData>, t: Throwable) {
                    ctx.toast("서버 통신에 실패하였습니다")
                    Log.e("Malibin Debug",t.toString())
                }

                override fun onResponse(
                    call: Call<GetChangeServerStatusResponseData>,
                    response: Response<GetChangeServerStatusResponseData>
                ) {
                    Log.v("Malibin Debug","응답은 왔는데 왜 안되냐? : "+response.body().toString())
                    if (response.isSuccessful) {

                        var branch: Int = response.body()!!.status

                        when (branch) {
                            200 -> {
                                Log.v("Malibin Debug", "서버 상태변경 성공")
                                //상태변경 성공인경우
                                (ctx as MainActivity).callDoneAddRVItem(dataList[position])

                                dataList.removeAt(position)
                                notifyItemRemoved(position)
                                notifyItemRangeChanged(position, dataList.size)
                                //notifyItemChanged(position)
                                //notifyDataSetChanged()
                            }
                        }
                    }
                }

            })
            //서버통신 그냥 때려박음 ㅋㅋ시발------------------------------------------------------------------------
            //메인액티비티에 있는 callDoneAddRVItem 메소드를 호출
            //그럼 액티비티에서는 DoneFragment에 있는 addRVItem 함수를 호출함



            Log.v("Malibin Debug", "btn_gave position : $position")
        }
    }


    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_date: TextView = itemView.findViewById(R.id.tv_rvitem_ongoing_date)
        var tv_time: TextView = itemView.findViewById(R.id.tv_rvitem_ongoing_time)
        var tv_orderNum: TextView = itemView.findViewById(R.id.tv_rvitem_ongoing_ordernum)
        var tv_nickname: TextView = itemView.findViewById(R.id.tv_rvitem_ongoing_nickname)
        var tv_menu: TextView = itemView.findViewById(R.id.tv_rvitem_ongoing_menu)
        var tv_sizeAmount: TextView = itemView.findViewById(R.id.tv_rvitem_ongoing_size_amount)
        var tv_cost: TextView = itemView.findViewById(R.id.tv_rvitem_ongoing_cost)
        var tv_request: TextView = itemView.findViewById(R.id.tv_rvitem_ongoing_request)

        var btn_ready: Button = itemView.findViewById(R.id.btn_rvitem_ongoing_ready)
        var btn_gave: Button = itemView.findViewById(R.id.btn_rvitem_ongoing_gave)
    }

    private fun customToast() {

        var toast = Toast.makeText(ctx, "제조 완료 알림이 전송되었습니다.", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)

        var group: ViewGroup = toast.view as ViewGroup

        var tv: TextView = group.getChildAt(0) as TextView
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)

        toast.show()

    }

    fun insertRVItem(item: OrderListData) {
        val positon: Int = dataList.size
        dataList.add(item)
        notifyItemInserted(positon)
    }
}