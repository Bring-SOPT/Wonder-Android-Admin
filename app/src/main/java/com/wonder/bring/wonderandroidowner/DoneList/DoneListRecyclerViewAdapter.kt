package com.wonder.bring.wonderandroidowner.DoneList

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.wonder.bring.wonderandroidowner.Network.Get.OrderListData
import com.wonder.bring.wonderandroidowner.R
import com.wonder.bring.wonderandroidowner.SizeConvertor
import java.text.SimpleDateFormat
import java.util.*

class DoneListRecyclerViewAdapter(var ctx: Context, val dataList: ArrayList<OrderListData>) :
    RecyclerView.Adapter<DoneListRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view : View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_done,parent,false)
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

        holder.tv_cost.text = (dataList[position].firstMenu.menuCountPrice.toString() + "원")
        holder.tv_request.text = dataList[position].firstMenu.memo

    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_date: TextView = itemView.findViewById(R.id.tv_rvitem_done_date)
        var tv_time: TextView = itemView.findViewById(R.id.tv_rvitem_done_time)
        var tv_orderNum: TextView = itemView.findViewById(R.id.tv_rvitem_done_ordernum)
        var tv_nickname: TextView = itemView.findViewById(R.id.tv_rvitem_done_nickname)
        var tv_menu: TextView = itemView.findViewById(R.id.tv_rvitem_done_menu)
        var tv_sizeAmount: TextView = itemView.findViewById(R.id.tv_rvitem_done_size_amount)
        var tv_cost: TextView = itemView.findViewById(R.id.tv_rvitem_done_cost)
        var tv_request: TextView = itemView.findViewById(R.id.tv_rvitem_done_request)

    }

    fun insertRVItem(item: OrderListData){
        val positon : Int = dataList.size
        dataList.add(item)
        notifyItemInserted(positon)
    }
}