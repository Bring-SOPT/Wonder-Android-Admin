package com.wonder.bring.wonderandroidowner

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import com.wonder.bring.wonderandroidowner.MainFragments.DoneFragment
import com.wonder.bring.wonderandroidowner.MainFragments.MainFragmentStatePagerAdapter
import com.wonder.bring.wonderandroidowner.MainFragments.OnGoingFragment
import com.wonder.bring.wonderandroidowner.Network.ApplicationController
import com.wonder.bring.wonderandroidowner.Network.Get.GetAllOrderListResponseData
import com.wonder.bring.wonderandroidowner.Network.Get.OrderListData
import com.wonder.bring.wonderandroidowner.Network.NetworkService
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    //듀듀커피 - 10  니어커피 - 11  탐탐 역삼2호점 - 6   던킨 역삼 4호점 - 5
    companion object {
        var storeIdx: Int = 11

    }


    var waitingOrderList: ArrayList<OrderListData> = ArrayList()
    var ongoingOrderList: ArrayList<OrderListData> = ArrayList()
    var doneOrderList: ArrayList<OrderListData> = ArrayList()

    lateinit var viewPagerAdapter: MainFragmentStatePagerAdapter

    // 보미 서버 통신
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        variableInit()
    }

    private fun variableInit() {

        networkService.getAllOrderListRequest("application/json", storeIdx)
            .enqueue(object : Callback<GetAllOrderListResponseData> {
                override fun onFailure(call: Call<GetAllOrderListResponseData>, t: Throwable) {
                    //toast("서버와 연결이 되어있지 않습니다.")
                    //toast(t.toString())
                    Log.v("Malibin Debug", t.localizedMessage)
                    Log.v("Malibin Debug", t.message)
                    //Log.v("Malibin Debug",t.stackTrace[0].)
                    //Log.v("Malibin Debug",t.suppressed.toString())

                    Log.v("Malibin Debug", t.cause.toString())
                }

                override fun onResponse(
                    call: Call<GetAllOrderListResponseData>,
                    response: Response<GetAllOrderListResponseData>
                ) {

                    if (response.body() == null) {

                        Log.v("Malibin Debug", "전체 서버에서 온 데이터는 \n" + response.toString())

                    } else {
                        for (data in response.body()!!.data) {

                            when (data.state) {
                                0 -> {
                                    waitingOrderList.add(data)
                                }
                                1, 2 -> {
                                    ongoingOrderList.add(data)
                                }
                                3 -> {
                                    doneOrderList.add(data)
                                }
                            }
                        }

                        Log.v("Malibin Debug", "전체 서버에서 온 데이터는 \n" + response.body().toString())

                        Log.v("Malibin Debug", "waitingOrderList 데이터는 \n" + waitingOrderList.toString())

                        Log.v("Malibin Debug", "ongoingOrderList 데이터는 \n" + ongoingOrderList.toString())

                        Log.v("Malibin Debug", "doneOrderList 데이터는 \n" + doneOrderList.toString())

                        configureBottomNavigation()
                    }
                }
            })

    }


    private fun configureBottomNavigation() {

        viewPagerAdapter = MainFragmentStatePagerAdapter(
            supportFragmentManager,
            3,
            waitingOrderList,
            ongoingOrderList,
            doneOrderList
        )

        vp_main_act_frag_pager.adapter = viewPagerAdapter
        vp_main_act_frag_pager.offscreenPageLimit = 3

        // ViewPager와 Tablayout을 엮어줍니다!
        tl_main_act_top_menu.setupWithViewPager(vp_main_act_frag_pager)
        //TabLayout에 붙일 layout을 찾아준 다음
        val bottomNaviLayout: View = this.layoutInflater.inflate(R.layout.top_navigation_tab, null, false)
        //탭 하나하나 TabLayout에 연결시켜줍니다.
        tl_main_act_top_menu.getTabAt(0)!!.customView =
                bottomNaviLayout.findViewById(R.id.btn_bottom_navi_main_tab) as RelativeLayout
        tl_main_act_top_menu.getTabAt(1)!!.customView =
                bottomNaviLayout.findViewById(R.id.btn_bottom_navi_map_tab) as RelativeLayout
        tl_main_act_top_menu.getTabAt(2)!!.customView =
                bottomNaviLayout.findViewById(R.id.btn_bottom_navi_my_page_tab) as RelativeLayout
    }


    //뷰페이저 어뎁터를 통해 ongoing fragment의 인스턴스를 얻어오고, 그 인스턴스를 캐스팅한후
    //ongoing fragment에 있는 함수인 addRVItem을 호출함
    //그럼 이제 ongoing fragment의 리사이클러 뷰에 아이템이 내려꽂힘.
    fun callOnGoingAddRVItem(item: OrderListData) {
        (viewPagerAdapter.getItem(1) as OnGoingFragment).addRVItem(item)
    }

    //위와 같은 로직으로 동작함.
    fun callDoneAddRVItem(item: OrderListData) {
        (viewPagerAdapter.getItem(2) as DoneFragment).addRVItem(item)
    }
}
