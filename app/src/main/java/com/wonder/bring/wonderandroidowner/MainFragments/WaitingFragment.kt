package com.wonder.bring.wonderandroidowner.MainFragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wonder.bring.wonderandroidowner.R
import com.wonder.bring.wonderandroidowner.OrderListData
import com.wonder.bring.wonderandroidowner.WaitingList.WaitingListRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_waiting.*

class WaitingFragment : Fragment() {
    lateinit var waitingListRecyclerViewAdapter: WaitingListRecyclerViewAdapter
    var dataList: ArrayList<OrderListData> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_waiting, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setRecyclerView()
    }

    private fun setRecyclerView() {
        setTempData()
        waitingListRecyclerViewAdapter = WaitingListRecyclerViewAdapter(activity!!,dataList)
        rv_waiting_frag_list.adapter = waitingListRecyclerViewAdapter
        rv_waiting_frag_list.layoutManager = LinearLayoutManager(activity)

    }

    private fun setTempData(){
        //임시데이터

        dataList.add(
            OrderListData(
                "2018.12.29",
                "14:06",
                1,
                "최재영",
                "아메리카노 ICE (Regular / 1개)",
                5000,
                "요청사항요청사항요청사항요청사항요청사항요청사항요청사항요청사항요청사항"
            )
        )
        dataList.add(
            OrderListData(
                "2018.12.29",
                "14:08",
                2,
                "모메",
                "아메리카노 ICE (Regular / 5개)",
                25000,
                "요청사항요청사항요청사항요청사항요청사항요청사항요청사항요청사항요청사항"
            )
        )
        dataList.add(
            OrderListData(
                "2018.12.29",
                "14:10",
                3,
                "진상1",
                "카페라떼 ICE (Regular / 1개)",
                5500,
                "카페라떼에 우유빼고 주세요 당연히 이날씨에 차가운거 안먹겟죠 따뜻한 아이스 카페라떼 준비해주세요 ^^"
            )
        )
        dataList.add(
            OrderListData(
                "2019.01.01",
                "01:23",
                4,
                "진상2",
                "스무디 (Regular / 2개)",
                12000,
                "차가운거 말고 따듯한 스무디로 해주세요^^ "
            )
        )
    }
}