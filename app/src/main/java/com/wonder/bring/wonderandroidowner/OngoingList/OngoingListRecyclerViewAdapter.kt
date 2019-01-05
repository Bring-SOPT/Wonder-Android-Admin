package com.wonder.bring.wonderandroidowner.OngoingList

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.wonder.bring.wonderandroidowner.OrderListData
import com.wonder.bring.wonderandroidowner.R
import com.wonder.bring.wonderandroidowner.WaitingList.OneLineMessageDialog


class OngoingListRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<OrderListData>) :
    RecyclerView.Adapter<OngoingListRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_ongoing, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tv_date.text = dataList[position].date
        holder.tv_time.text = dataList[position].time
        holder.tv_orderNum.text = dataList[position].orderNum.toString()
        holder.tv_nickname.text = dataList[position].nickname
        holder.tv_menu.text = dataList[position].menu
        holder.tv_cost.text = dataList[position].cost.toString() + "원"
        holder.tv_request.text = dataList[position].request

        holder.btn_ready.setOnClickListener {
            val oneLineMessageDialog = OneLineMessageDialog(ctx, "제조 완료 알림이 전송되었습니다.")
            oneLineMessageDialog.show()

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
        var tv_cost: TextView = itemView.findViewById(R.id.tv_rvitem_ongoing_cost)
        var tv_request: TextView = itemView.findViewById(R.id.tv_rvitem_ongoing_request)

        var btn_ready: Button = itemView.findViewById(R.id.btn_rvitem_ongoing_ready)
        var btn_gave: Button = itemView.findViewById(R.id.btn_rvitem_ongoing_gave)
    }
}