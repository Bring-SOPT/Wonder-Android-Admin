package com.wonder.bring.wonderandroidowner.WaitingList

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.wonder.bring.wonderandroidowner.R

class WaitingListRecyclerViewAdapter(var ctx: Context, val dataList: ArrayList<WaitingListData>) :
    RecyclerView.Adapter<WaitingListRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view : View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_waiting,parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tv_date.text = dataList[position].date
        holder.tv_time.text = dataList[position].time
        holder.tv_orderNum.text = dataList[position].orderNum.toString()
        holder.tv_nickname.text = dataList[position].nickname
        holder.tv_menu.text = dataList[position].menu
        holder.tv_cost.text = dataList[position].cost.toString()+"원"
        holder.tv_request.text = dataList[position].request

        holder.btn_accept.setOnClickListener {
            val waitingAcceptDialog :WaitingAcceptDialog = WaitingAcceptDialog(ctx)
            waitingAcceptDialog.show()
        }

        holder.btn_deny.setOnClickListener {
            val waitingDenyDialog = WaitingDenyDialog(ctx)
            waitingDenyDialog.show()
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_date: TextView = itemView.findViewById(R.id.tv_rvitem_waiting_date)
        var tv_time: TextView = itemView.findViewById(R.id.tv_rvitem_waiting_time)
        var tv_orderNum: TextView = itemView.findViewById(R.id.tv_rvitem_waiting_ordernum)
        var tv_nickname: TextView = itemView.findViewById(R.id.tv_rvitem_waiting_nickname)
        var tv_menu: TextView = itemView.findViewById(R.id.tv_rvitem_waiting_menu)
        var tv_cost: TextView = itemView.findViewById(R.id.tv_rvitem_waiting_cost)
        var tv_request: TextView = itemView.findViewById(R.id.tv_rvitem_waiting_request)

        var btn_accept: Button = itemView.findViewById(R.id.btn_rvitem_waiting_accept)
        var btn_deny: Button = itemView.findViewById(R.id.btn_rvitem_waiting_deny)
    }
}