package com.wonder.bring.wonderandroidowner.MainFragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wonder.bring.wonderandroidowner.MainActivity
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


        //(activity as MainActivity).callOnGoingMethod()
    }


}