package com.wonder.bring.wonderandroidowner.OngoingList

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.wonder.bring.wonderandroidowner.Network.Get.OrderListData
import com.wonder.bring.wonderandroidowner.R
import com.wonder.bring.wonderandroidowner.SizeConvertor
import com.wonder.bring.wonderandroidowner.WaitingList.OneLineMessageDialog
import java.text.SimpleDateFormat
import java.util.*


class OngoingListRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<OrderListData>) :
    RecyclerView.Adapter<OngoingListRecyclerViewAdapter.Holder>() {
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
                (SizeConvertor.parseSizeString(dataList[position].firstMenu.size) + " / " + dataList[position].firstMenu.orderCount+"개")
        holder
        holder.tv_cost.text = (dataList[position].firstMenu.menuCountPrice.toString() + "원")
        holder.tv_request.text = dataList[position].firstMenu.memo

        holder.btn_ready.setOnClickListener {

            customToast()

            holder.btn_ready.isEnabled = false
            holder.btn_gave.isEnabled = true
        }

        holder.btn_gave.setOnClickListener {
            dataList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,dataList.size)
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

    private fun customToast(){

        var toast = Toast.makeText(ctx,"제조 완료 알림이 전송되었습니다.", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER,0,0)

        var group: ViewGroup = toast.view as ViewGroup

        var tv : TextView = group.getChildAt(0) as TextView
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)

        toast.show()

    }
}