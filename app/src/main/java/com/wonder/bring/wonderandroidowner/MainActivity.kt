package com.wonder.bring.wonderandroidowner

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import com.wonder.bring.wonderandroidowner.MainFragments.MainFragmentStatePagerAdapter
import com.wonder.bring.wonderandroidowner.Network.ApplicationController
import com.wonder.bring.wonderandroidowner.Network.NetworkService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // 보미 서버 통신
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureBottomNavigation()
    }

    private fun variableInit(){

    }



    private fun configureBottomNavigation(){
        vp_main_act_frag_pager.adapter = MainFragmentStatePagerAdapter(supportFragmentManager, 3)
        vp_main_act_frag_pager.offscreenPageLimit = 3

        // ViewPager와 Tablayout을 엮어줍니다!
        tl_main_act_top_menu.setupWithViewPager(vp_main_act_frag_pager)
        //TabLayout에 붙일 layout을 찾아준 다음
        val bottomNaviLayout : View = this.layoutInflater.inflate(R.layout.top_navigation_tab, null, false)
        //탭 하나하나 TabLayout에 연결시켜줍니다.
        tl_main_act_top_menu.getTabAt(0)!!.customView = bottomNaviLayout.findViewById(R.id.btn_bottom_navi_main_tab) as RelativeLayout
        tl_main_act_top_menu.getTabAt(1)!!.customView = bottomNaviLayout.findViewById(R.id.btn_bottom_navi_map_tab) as RelativeLayout
        tl_main_act_top_menu.getTabAt(2)!!.customView = bottomNaviLayout.findViewById(R.id.btn_bottom_navi_my_page_tab) as RelativeLayout
    }
}
