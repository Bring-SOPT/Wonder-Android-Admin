package com.wonder.bring.wonderandroidowner.MainFragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wonder.bring.wonderandroidowner.Network.Get.FirstMenu
import com.wonder.bring.wonderandroidowner.R
import com.wonder.bring.wonderandroidowner.Network.Get.OrderListData
import com.wonder.bring.wonderandroidowner.WaitingList.WaitingListRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_waiting.*
import java.text.SimpleDateFormat
import java.util.*

class WaitingFragment : Fragment() {

    companion object {
        private var instance: WaitingFragment? = null
        @Synchronized
        fun getInstance(data: ArrayList<OrderListData>): WaitingFragment {
            if (instance == null) {
                instance = WaitingFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable("data", data)
                    }
                }
            }
            return instance!!
        }
    }

    lateinit var waitingListRecyclerViewAdapter: WaitingListRecyclerViewAdapter
    var dataList: ArrayList<OrderListData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_waiting, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setRecyclerView()
    }

    private fun setRecyclerView() {
        //setTempData()
        waitingListRecyclerViewAdapter = WaitingListRecyclerViewAdapter(activity!!, dataList)
        rv_waiting_frag_list.adapter = waitingListRecyclerViewAdapter
        rv_waiting_frag_list.layoutManager = LinearLayoutManager(activity)

    }

    private fun setData() {
        arguments?.let {
            dataList = it.getSerializable("data") as ArrayList<OrderListData>
            Log.v("Malibin Debug", "waiting fragment에 온 데이터 아규먼트가 널이니??" + dataList.toString())
        }

        Log.v("Malibin Debug", "waiting fragment에 온 데이터" + dataList.toString())


    }

    private fun setTempData() {
        //임시데이터

        var date = "2019-01-04 16:12:25"

        //var format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        //var date: Date = format.parse("2019-01-04 16:12:25")
        dataList.add(
            OrderListData(
                20,
                "daye",
                1,
                date,
                13000,
                2,
                FirstMenu(
                    "소시지 인 치즈 베이글",
                    4,
                    2,
                    5000,
                    "오늘도 맛있는 음식을 위해 고생하시는 모든 분께 감사인사 드려요^^,,"
                )
            )
        )

        //date = format.parse("2019-01-06 20:45:58")
        dataList.add(
            OrderListData(
                29,
                "bogum",
                0,
                date,
                19600,
                1,
                FirstMenu(
                    "카페모카ICE",
                    2,
                    2,
                    9800,
                    "좋아요~"
                )
            )
        )


    }
}