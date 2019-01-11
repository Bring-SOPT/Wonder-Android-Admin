package com.wonder.bring.wonderandroidowner.MainFragments

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.wonder.bring.wonderandroidowner.Network.Get.OrderListData

class MainFragmentStatePagerAdapter(
    fm: FragmentManager,
    val fragmentCount: Int,
    val waitData: ArrayList<OrderListData>,
    val ongoingData: ArrayList<OrderListData>,
    val doneData: ArrayList<OrderListData>
) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return WaitingFragment.getInstance(waitData)
            1 -> return OnGoingFragment.getInstance(ongoingData)
            2 -> return DoneFragment.getInstance(doneData)
            else -> return null
        }
    }

    override fun getCount(): Int = fragmentCount


}