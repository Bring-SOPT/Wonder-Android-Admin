package com.wonder.bring.wonderandroidowner.MainFragments

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class MainFragmentStatePagerAdapter(fm : FragmentManager,val fragmentCount: Int): FragmentStatePagerAdapter(fm){
    override fun getItem(position: Int): Fragment? {
        when(position){
            0 -> return WaitingFragment()
            1 -> return OnGoingFragment()
            2 -> return DoneFragment()
            else -> return null
        }
    }
    override fun getCount(): Int = fragmentCount


}