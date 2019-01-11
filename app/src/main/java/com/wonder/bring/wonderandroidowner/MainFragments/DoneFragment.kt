package com.wonder.bring.wonderandroidowner.MainFragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wonder.bring.wonderandroidowner.DoneList.DoneListRecyclerViewAdapter
import com.wonder.bring.wonderandroidowner.Network.Get.FirstMenu
import com.wonder.bring.wonderandroidowner.Network.Get.OrderListData
import com.wonder.bring.wonderandroidowner.R
import kotlinx.android.synthetic.main.fragment_done.*
import java.text.SimpleDateFormat
import java.util.*

class DoneFragment : Fragment() {

    companion object {
        private var instance: DoneFragment? = null
        @Synchronized
        fun getInstance(data: ArrayList<OrderListData>): DoneFragment {
            if (instance == null) {
                instance = DoneFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable("data", data)
                    }
                }
            }
            return instance!!
        }
    }

    lateinit var doneListRecyclerViewAdapter: DoneListRecyclerViewAdapter
    var dataList: ArrayList<OrderListData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_done, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setRecyclerView()
    }

    private fun setRecyclerView() {
        // setTempData()
        doneListRecyclerViewAdapter = DoneListRecyclerViewAdapter(activity!!, dataList)
        rv_done_frag_list.adapter = doneListRecyclerViewAdapter
        rv_done_frag_list.layoutManager = LinearLayoutManager(activity)

    }

    private fun setData() {
        arguments?.let {
            dataList = it.getSerializable("data") as ArrayList<OrderListData>
            Log.v("Malibin Debug", "done fragment에 온 데이터 아규먼트가 널이니??" + dataList.toString())
        }

        Log.v("Malibin Debug", "done fragment에 온 데이터" + dataList.toString())


    }

    //리사이클러뷰 어뎁터 안에 있는 아이템 삽입 함수를 호출함
    fun addRVItem(item: OrderListData) {
        doneListRecyclerViewAdapter.insertRVItem(item)
    }
}